/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

//import clube.socios.controlesociosclube.db.SocioDAO;
import clube.socios.controlesociosclube.model.DAO.Endpoint;
import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class SocioBO {

    public static Socio consultaDadosSocio(String nome) throws ItemNotFoundException, ItemNotFoundHereException {
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject("nome:" + nome);
            if (retrieveObject == null) {
                throw new ItemNotFoundException();
            }
            return retrieveObject;
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            Collection<Socio> list = persistence.list();
            for (Socio socio : list) {
                if (socio.getNome().contains(nome)) {
                    return socio;
                }
            }
            throw new ItemNotFoundHereException();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Nao consulta localmente devido ao id ainda na haver sido atribuido.
     *
     * @param idSocio
     * @return
     * @throws clube.socios.controlesociosclube.model.BO.ItemNotFoundException
     * @throws clube.socios.controlesociosclube.model.BO.LoseConnectionException
     */
    public static Socio consultaDadosSocio(int idSocio) throws ItemNotFoundException, LoseConnectionException {
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject("id:" + idSocio);
            if (retrieveObject == null) {
                throw new ItemNotFoundException();
            }
            return retrieveObject;
        } catch (IOException ex) {
            throw new LoseConnectionException();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Boolean pagamentosEmDia() {
        throw new UnsupportedOperationException();
    }

    public static Socio obterSocio(String cpf) throws ItemNotFoundException, ItemNotFoundHereException {
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject("cpf:" + cpf);
            if (retrieveObject == null) {
                throw new ItemNotFoundException();
            }
            return retrieveObject;
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            Socio socio = new Socio(cpf, cpf, cpf, Sexo.FEMININO, null, null);
            try {
                return (Socio) persistence.retrieve(socio);
            } catch (ItemNotFoundException ex1) {
                throw new ItemNotFoundHereException();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void atualizarSocio(Socio socio) throws ItemNotFoundHereException, ItemNotFoundException {
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject("cpf:" + socio.getCpf());
            if (retrieveObject == null) {
                throw new ItemNotFoundException();
            }
            Endpoint.sendObject(socio);
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            try {
                persistence.update(socio);
            } catch (ItemNotFoundException ex1) {
                throw new ItemNotFoundHereException();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean gravaSocio(Socio socio) throws ItemAlreadyExistException {
        try {
            Socio retrieveObject = (Socio) Endpoint.retrieveObject("cpf:" + socio.getCpf());
            if (retrieveObject == null) {
                Endpoint.sendObject(socio);
                return true;
            }
            throw new ItemAlreadyExistException();
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            try {
                persistence.retrieve(socio);
                throw new ItemAlreadyExistException();
            } catch (ItemNotFoundException ex1) {
                persistence.create(socio);
                return true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Collection<Socio> listar() {
        try {
            Collection<Socio> retrieveObject = (Collection<Socio>) Endpoint.retrieveObject("list");
            return retrieveObject;
        } catch (IOException ex) {
            System.err.println("Conexao perdida");
            SerializePersistence persistence = new SerializePersistence();
            return persistence.list();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
