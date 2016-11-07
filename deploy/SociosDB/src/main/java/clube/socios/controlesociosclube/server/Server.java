/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor apto a receber requisiçoes via socket e persisti-las na base de
 * dados. Nenhuma forma de segurança e provida.
 *
 * @author romulo
 */
public class Server {

    /**
     * Inicializa o servidor e deixa-o pronto para receber quantas conexoes
     * houverem.
     *
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(10000);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Worker worker = new Worker(clientSocket);
                Thread workerThread = new Thread(worker);
                workerThread.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
