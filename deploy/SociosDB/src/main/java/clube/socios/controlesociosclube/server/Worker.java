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
    private final SocioDAO socioDAO = new SocioDAO();

    public Worker(Socket clientSocket) {
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
                String requestString = (String) request;
                if (requestString.equals("list")) {
                    Collection<Socio> list = socioDAO.list();
                    outputStream.flush();
                    outputStream.writeObject(list);
                }
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
