package clube.socios.controlesociosclube.server;

import clube.socios.controlesociosclube.db.SocioDAO;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.persistence.NoResultException;

/**
 * Thread que obtem a requisiçao de um clinte e produz a resposta.
 *
 * @author romulo
 */
public class Worker implements Runnable {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket clientSocket;
    private final SocioDAO socioDAO;

    /**
     * Constroi o objeto dado uma conexao com o cliente.
     *
     * @param clientSocket
     */
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

    /**
     * Obtem a resposta, processa-a e envia resposta, caso houver.
     */
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
                } else if (requestString.startsWith("cpf:")) {
                    try {
                        response = socioDAO.obter(requestString.replaceFirst("cpf:", ""));
                    } catch (NoResultException ex) {
                        response = null;
                    }
                } else if (requestString.startsWith("id:")) {
                    try {
                        response = socioDAO.obter(Integer.valueOf(requestString.replace("id:", "")));
                    } catch (NumberFormatException ex) {
                        response = null;
                    }
                } else if (requestString.startsWith("nome:")) {
                    try {
                        response = socioDAO.obterPeloNome(requestString.replaceFirst("nome:", ""));
                    } catch (NoResultException ex) {
                        response = null;
                    }
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
