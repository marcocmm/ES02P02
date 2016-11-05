/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.DAO.Serviços.SincronizaDados;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author romulo
 */
public class Main extends JFrame {

    public Main() {
        JPanel center = new JPanel(new GridLayout(4, 1));

        JButton addSocioButton = new JButton("Adicionar Socio");
        addSocioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CRUDSocio();
            }
        });

        JButton checkinSocioButton = new JButton("Check-in Socio");
        JButton pagarMensalidadeButton = new JButton("Pagar Mensalidade");

        center.add(addSocioButton);
        center.add(checkinSocioButton);
        center.add(pagarMensalidadeButton);

        add(center);
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
        new Main();
    }
}
