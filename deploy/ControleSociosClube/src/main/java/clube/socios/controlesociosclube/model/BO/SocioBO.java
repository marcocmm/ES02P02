/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

//import clube.socios.controlesociosclube.db.SocioDAO;
import clube.socios.controlesociosclube.model.DAO.Endpoint;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.IOException;
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
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject(socio.getCpf());
            if (retrieveObject == null) {
                Endpoint.sendObject(socio);
            }
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            persistence.create(socio);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
