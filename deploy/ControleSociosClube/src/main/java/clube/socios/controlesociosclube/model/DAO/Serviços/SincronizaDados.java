/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.DAO.Serviços;

import clube.socios.controlesociosclube.model.BO.ItemNotFoundException;
import clube.socios.controlesociosclube.model.BO.SerializePersistence;
import clube.socios.controlesociosclube.model.DAO.Endpoint;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class SincronizaDados {

    public void updateSocios() {
        SerializePersistence persistence = new SerializePersistence();
        Collection<Socio> list = persistence.list();
        try {
            for (Socio socio : list) {
                Socio retrieveObject = (Socio) Endpoint.retrieveObject(socio.getCpf());
                if (retrieveObject == null) {
                    Endpoint.sendObject(socio);
                }
                persistence.delete(socio);
            }
        } catch (IOException ex) {
            System.err.println("Conexao ainda nao reestabelecida");
        } catch (ItemNotFoundException ex) {
            Logger.getLogger(SincronizaDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SincronizaDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCheckins() {
        throw new UnsupportedOperationException();
    }
}
