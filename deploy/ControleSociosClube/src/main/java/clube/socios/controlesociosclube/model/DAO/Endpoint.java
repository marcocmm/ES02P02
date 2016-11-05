/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author romulo
 */
public class Endpoint {

    public static Object retrieveObject(String whichObject) throws IOException, ClassNotFoundException {
        Object response = null;
        Socket socket = new Socket(InetAddress.getByName("localhost"), 10000);

        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        outputStream.flush();
        outputStream.writeObject(whichObject);

        response = inputStream.readObject();

        inputStream.close();
        outputStream.close();
        socket.close();

        return response;
    }

    public static void sendObject(Object request) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 10000);

        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        outputStream.flush();
        outputStream.writeObject(request);

        outputStream.close();
        socket.close();
    }
}
