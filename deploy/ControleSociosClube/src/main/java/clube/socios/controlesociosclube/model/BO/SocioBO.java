/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

//import clube.socios.controlesociosclube.db.SocioDAO;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class SocioBO {

    public Socio consultaDadosSocio(String nome) {
        return null;
    }

    public Socio consultaDadosSocio(int idSocio) {
        return null;
    }

    public Boolean pagamentosEmDia() {
        throw new UnsupportedOperationException();
    }

    public static void gravaSocio(Socio socio) {
        sendObject(socio);
    }

    private static Object retrieveObject(String whichObject) {
        Object response = null;
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 10000);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.flush();
            outputStream.writeObject(whichObject);

            response = inputStream.readObject();

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return response;
        }
    }

    private static void sendObject(Object request) {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 10000);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.flush();
            outputStream.writeObject(request);

            outputStream.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
