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
import clube.socios.controlesociosclube.model.entidades.Dependente;
import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
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
public class DependentesGUI extends JFrame {

    public DependentesGUI(Socio socio) {
        setLayout(new BorderLayout());

        String[] colunas = {"Nome", "Sexo", "Idade"};

        Object[][] dados = new Object[10][3];

        {
            Collection<Dependente> dependentes = socio.getDependentes();
            int i = 0;
            for (Dependente dependente : dependentes) {
                dados[i][0] = dependente.getNome();
                dados[i][1] = dependente.getSexo();
                dados[i][2] = dependente.getIdade();
                i++;
            }
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(dados, colunas);

        JPanel center = new JPanel(new GridLayout(1, 1));

        JTable tabela = new JTable(defaultTableModel);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        center.add(barraRolagem);

        JButton cadastrarButton = new JButton("Adicionar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                }
                String nome = (String) tabela.getValueAt(selectedRow, 0);
                String sexo = (String) tabela.getValueAt(selectedRow, 1);
                String idade = (String) tabela.getValueAt(selectedRow, 2);

                if (nome == null || sexo == null || idade == null) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Dependente dependente = new Dependente();
                    dependente.setNome(nome);
                    dependente.setSexo(sexo.equals("MASCULINO") ? Sexo.FEMININO : Sexo.FEMININO);
                    dependente.setIdade(Integer.valueOf(idade));

                    socio.addDependente(dependente);

                    SocioBO.atualizarSocio(socio);
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Atualizar", JOptionPane.ERROR_MESSAGE);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Atualizar", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Insira corretamente os dados", "Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        JButton removerButton = new JButton("Remover");

        removerButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                }
                String nome = (String) tabela.getValueAt(selectedRow, 0);
                Dependente dependente = new Dependente();
                dependente.setNome(nome);
                socio.removeDependente(dependente);

                try {
                    SocioBO.atualizarSocio(socio);
                    defaultTableModel.removeRow(selectedRow);
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Socio nao encontrado", "Atualizar", JOptionPane.ERROR_MESSAGE);
                    socio.addDependente(dependente);
                } catch (ItemNotFoundHereException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Conxao perdida\nSocio nao encontrado localmente", "Atualizar", JOptionPane.ERROR_MESSAGE);
                    socio.addDependente(dependente);
                }
            }
        });

        JToolBar toolbar = new JToolBar("Oções");
        toolbar.add(cadastrarButton);
        toolbar.add(removerButton);
        
        add(toolbar, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        
        setTitle("Gerenciar Dependentes");
        setResizable(false);
        setSize(500, 120);
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
        new DependentesGUI(socio);
    }

}
