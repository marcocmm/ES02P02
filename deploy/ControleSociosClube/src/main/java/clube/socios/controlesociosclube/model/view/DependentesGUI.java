/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.entidades.Dependente;
import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
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

        String[] colunas = {"Nome", "Telefone", "Email"};

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
        JButton removerButton = new JButton("Remover");
        JButton atualizarButton = new JButton("Atualizar");

        JToolBar toolbar = new JToolBar("Oções");
        toolbar.add(cadastrarButton);
        toolbar.add(removerButton);
        toolbar.add(atualizarButton);

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
