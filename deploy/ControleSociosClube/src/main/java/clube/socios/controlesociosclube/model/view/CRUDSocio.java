/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.BO.SocioBO;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author romulo
 */
public class CRUDSocio extends JFrame {

    JTextField cpfTextField;

    public CRUDSocio() {
        cpfTextField = new JTextField();

        setLayout(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(4, 2));
        center.add(new JLabel("CPF"));
        center.add(cpfTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfTextField.getText();
                String nome = null;
                Collection<Unidade> unidades = new ArrayList<>();
                Collection<Atividade> atividades = new ArrayList<>();
                Socio socio = new Socio(cpf, nome, WIDTH, Sexo.FEMININO, unidades, atividades);
                SocioBO.gravaSocio(socio);
            }
        });

        JButton bloquearButton = new JButton("Bloquear");
        JButton atualizarButton = new JButton("Atualizar");
        JButton listarButton = new JButton("Ver todos");

        JToolBar toolbar = new JToolBar("Oções");
        toolbar.add(cadastrarButton);
        toolbar.add(bloquearButton);
        toolbar.add(atualizarButton);
        toolbar.add(listarButton);

        add(toolbar, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        setTitle("Gerenciar Socios");
        setResizable(false);
        setSize(700, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
