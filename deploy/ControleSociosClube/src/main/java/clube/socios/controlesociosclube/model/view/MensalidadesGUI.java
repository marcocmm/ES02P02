/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.BO.ItemNotFoundException;
import clube.socios.controlesociosclube.model.BO.ItemNotFoundHereException;
import clube.socios.controlesociosclube.model.BO.SocioBO;
import clube.socios.controlesociosclube.model.entidades.Dependente;
import clube.socios.controlesociosclube.model.entidades.Mensalidade;
import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author romulo
 */
public class MensalidadesGUI extends JFrame {

    private int quantidadeMensalidades;

    public MensalidadesGUI(Socio socio) {
        setLayout(new BorderLayout());

        String[] colunas = {"Vencimento", "Valor", "Paga"};

        Collection<Mensalidade> mensalidades = socio.getMensalidades();

        this.quantidadeMensalidades = mensalidades.size();
        String[][] dados = new String[quantidadeMensalidades + 1][3];

        {
            int i = 0;
            for (Mensalidade mensalidade : mensalidades) {
                dados[i][0] = mensalidade.getData().toString();
                dados[i][1] = String.valueOf(mensalidade.getValorMensalidade());
                dados[i][2] = String.valueOf(mensalidade.isPaga());
                i++;
            }
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(dados, colunas);

        JPanel center = new JPanel(new GridLayout(1, 1));

        JTable tabela = new JTable(defaultTableModel);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        center.add(barraRolagem);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = (String) tabela.getValueAt(quantidadeMensalidades, 0);
                String valor = (String) tabela.getValueAt(quantidadeMensalidades, 1);
                String paga = (String) tabela.getValueAt(quantidadeMensalidades, 2);

                if (data == null || valor == null || paga == null) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Mensalidade mensalidade = new Mensalidade();
                    
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date parse = simpleDateFormat.parse(data);
                    
                    mensalidade.setData(parse);
                    mensalidade.setPaga(paga.equals("true"));
                    socio.addMensalidade(mensalidade);
                    
                    SocioBO.atualizarSocio(socio);
                    quantidadeMensalidades++;
                    defaultTableModel.setNumRows(quantidadeMensalidades);
                    JOptionPane.showMessageDialog(rootPane, "Mensalidade registrada");
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Atualizar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conexao perdida\nSocio nao encontrado localmente", "Atualizar", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        JToolBar toolbar = new JToolBar("Oções");
        toolbar.add(salvarButton);

        add(toolbar, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        setTitle("Mensalidades");
        setResizable(true);
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Socio socio = new Socio("123456", "Nome", "Nome", Sexo.FEMININO, null, null);
        Dependente dependente1 = new Dependente();
        dependente1.setIdade(11);
        dependente1.setNome("Ana");
        dependente1.setSexo(Sexo.FEMININO);
        socio.addDependente(dependente1);
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setData(new Date());
        mensalidade.setPaga(Boolean.TRUE);
        mensalidade.setValorMensalidade(200);
        socio.addMensalidade(mensalidade);
        new MensalidadesGUI(socio);
    }

}
