package clube.socios.controlesociosclube.model.view;

import clube.socios.controlesociosclube.model.entidades.Socio;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.ScrollPane;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

// @author Radames
public class GUIListagem extends JDialog {

    JPanel painelTa = new JPanel();
    ScrollPane scroll = new ScrollPane();
    JTextArea ta = new JTextArea();

    public GUIListagem(List<Socio> texto) {
        setTitle("Listagem");
        setSize(500, 180);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();

        ta.setText("");
        for (int i = 0; i < texto.size(); i++) {
            ta.append(texto.get(i).getIdSocio() + " - " + texto.get(i).getNome() + " - " + texto.get(i).getCpf() + "\n");
        }

        scroll.add(ta);
        painelTa.add(scroll);

        cp.add(toolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);

        setLocationRelativeTo(null); // posiciona no centro da tela principal
        setVisible(true);//faz a janela ficar visível        
    }
}
