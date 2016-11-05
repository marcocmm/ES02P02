package clube.socios.controlesociosclube.server;

import clube.socios.controlesociosclube.db.SocioDAO;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

public class Worker implements Runnable {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket clientSocket;
    private final SocioDAO socioDAO;

    public Worker(Socket clientSocket) {
        this.socioDAO = new SocioDAO();
        try {
            this.clientSocket = clientSocket;
            this.outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Erro ao estabelecer fluxo de dados");
        }
    }

    @Override
    public void run() {
        Object request;
        try {
            request = inputStream.readObject();

            if (request instanceof String) {
                Object response = null;
                String requestString = (String) request;
                if (requestString.equals("list")) {
                    response = socioDAO.list();
                } else {
                    response = socioDAO.obter(requestString);
                }
                outputStream.flush();
                outputStream.writeObject(response);
            } else if (request instanceof Socio) {
                socioDAO.update((Socio) request);
            }

            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("leitura: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("classnotfound: " + e.getMessage());
        }
    }
}
