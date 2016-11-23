/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.BO.ItemAlreadyExistException;
import clube.socios.controlesociosclube.model.BO.ItemNotFoundException;
import clube.socios.controlesociosclube.model.BO.ItemNotFoundHereException;
import clube.socios.controlesociosclube.model.BO.SocioBO;
import clube.socios.controlesociosclube.model.DAO.Serviços.SincronizaDados;
import clube.socios.controlesociosclube.model.entidades.Atividade;
import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import clube.socios.controlesociosclube.model.entidades.Unidade;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author romulo
 */
public class CRUDSocio extends JFrame {

    public CRUDSocio() {
        JTextField idTextField = new JTextField();
        JTextField nomeTextField = new JTextField();
        JTextField cpfTextField = new JTextField();
        JTextField nascimentoTextField = new JTextField();
        JTextField sexoTextField = new JTextField();

        setLayout(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(6, 2));
        center.add(new JLabel("Id"));
        center.add(idTextField);
        center.add(new JLabel("Nome"));
        center.add(nomeTextField);
        center.add(new JLabel("CPF"));
        center.add(cpfTextField);
        center.add(new JLabel("Nascimento"));
        center.add(nascimentoTextField);
        center.add(new JLabel("Sexo"));
        center.add(sexoTextField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTextField.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Busca", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Socio obterSocio = SocioBO.obterSocio(cpf);
                    idTextField.setText(String.valueOf(obterSocio.getIdSocio()));
                    nomeTextField.setText(obterSocio.getNome());
                    cpfTextField.setText(obterSocio.getCpf());
                    nascimentoTextField.setText(obterSocio.getNascimento());
                    sexoTextField.setText(obterSocio.getSexo().name());
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Buscar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Buscar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String cpf = cpfTextField.getText();
                String nascimento = nascimentoTextField.getText();
                Sexo sexo = sexoTextField.getText().equals("FEMININO") ? Sexo.FEMININO : Sexo.MASCULINO;
                if (nome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Collection<Unidade> unidades = new ArrayList<>();
                Collection<Atividade> atividades = new ArrayList<>();
                Socio socio = new Socio(cpf, nome, nascimento, sexo, unidades, atividades);
                try {
                    boolean gravaSocio = SocioBO.gravaSocio(socio);
                    if (gravaSocio) {
                        JOptionPane.showMessageDialog(rootPane, "Socio cadastrado");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Erro", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ItemAlreadyExistException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio ja cadastrado", "Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String cpf = cpfTextField.getText();
                String nascimento = nascimentoTextField.getText();
                Sexo sexo = sexoTextField.getText().equals("FEMININO") ? Sexo.FEMININO : Sexo.MASCULINO;
                if (nome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Socio socio;
                try {
                    socio = SocioBO.obterSocio(cpf);
                    socio.setNome(nome);
                    socio.setNascimento(nascimento);
                    socio.setSexo(sexo);
                    SocioBO.atualizarSocio(socio);
                    JOptionPane.showMessageDialog(rootPane, "Cadastro atualizado");
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Atualizar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Atualizar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton listarButton = new JButton("Ver todos");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collection<Socio> listar = SocioBO.listar();
                JList jList = new JList(listar.toArray());
                GUIListagem guiListagem = new GUIListagem((List<Socio>) listar);
            }
        });

        JButton dependentesButton = new JButton("Dependentes");
        dependentesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTextField.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Busca", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Socio obterSocio = SocioBO.obterSocio(cpf);
                    idTextField.setText(String.valueOf(obterSocio.getIdSocio()));
                    nomeTextField.setText(obterSocio.getNome());
                    cpfTextField.setText(obterSocio.getCpf());
                    nascimentoTextField.setText(obterSocio.getNascimento());
                    sexoTextField.setText(obterSocio.getSexo().name());

                    new DependentesGUI(obterSocio);

                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Buscar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Buscar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton checkinSocioButton = new JButton("Check-in");
        
        JButton pagarMensalidadeButton = new JButton("Mensalidades");
        pagarMensalidadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTextField.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Busca", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Socio obterSocio = SocioBO.obterSocio(cpf);
                    idTextField.setText(String.valueOf(obterSocio.getIdSocio()));
                    nomeTextField.setText(obterSocio.getNome());
                    cpfTextField.setText(obterSocio.getCpf());
                    nascimentoTextField.setText(obterSocio.getNascimento());
                    sexoTextField.setText(obterSocio.getSexo().name());

                    new MensalidadesGUI(obterSocio);

                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Buscar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Buscar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton atividadesMensalidadeButton = new JButton("Atividades");

        JToolBar toolbar = new JToolBar("Oções");
        toolbar.add(buscarButton);
        toolbar.add(cadastrarButton);
        toolbar.add(atualizarButton);
        toolbar.add(listarButton);
        toolbar.add(dependentesButton);
        toolbar.add(checkinSocioButton);
        toolbar.add(pagarMensalidadeButton);
        toolbar.add(atividadesMensalidadeButton);

        add(toolbar, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        setTitle("Controle de Socios do Clube");
        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SincronizaDados sincronizaDados = new SincronizaDados();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sincronizaDados.updateSocios();
            }
        }, 0, 60000);
        new CRUDSocio();
    }

}
