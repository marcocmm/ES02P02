/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Persistencia local via serializaçao de dados.
 *
 * @author romulo
 * @param <T>
 */
public class SerializePersistence<T extends Serializable> implements Persistence<T> {

    /**
     * Colecao de itens que serao manipulados.
     */
    private Collection<T> items;

    /**
     * Local padrao para serializaçao da coleçao.
     */
    private static final String DB_URL = "/data/documents/workspace/ES02P02/deploy/database";

    /**
     * Constroi a persistencia resgatando as infomaçoes ja existentes.
     */
    public SerializePersistence() {
        Collection<T> items;
        try {
            items = deserialize();
        } catch (IOException | ClassNotFoundException ex) {
            items = new ArrayList<>();
            try {
                serialize(items);
            } catch (IOException ex1) {
                Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        this.items = items;
    }

    /**
     * Grava os dados no disco.
     *
     * @param items Coleçao de itens que serao persistidos.
     * @throws IOException
     */
    private synchronized final void serialize(Collection<T> items) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DB_URL);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(items);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Resgata os dados do disco.
     *
     * @return Retorna a coleçao dos items obtidos.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private synchronized final Collection<T> deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(DB_URL);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Collection<T> items = (Collection<T>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return items;
    }

    /**
     * Obtem todos os itens existentes na base. Metodo com lock exclusivo para a
     * mesma instancia.
     *
     * @return
     */
    @Override
    public synchronized final Collection<T> list() {
        try {
            items = deserialize();
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    /**
     * Insere um item na base.
     *
     * @param t
     */
    @Override
    public synchronized final void create(T t) {
        try {
            items = deserialize();
            items.add(t);
            serialize(items);
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtem um item na base.
     *
     * @param t
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public synchronized final T retrieve(T t) throws ItemNotFoundException {
        try {
            items = deserialize();
            for (T item : items) {
                if (item.equals(t)) {
                    return item;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new ItemNotFoundException("Item não encontrado");
    }

    /**
     * Atualiza um item buscando-o pela chave primaria. A chave primaria devera
     * ser descrita no metodo equals de cada classe.
     *
     * @param t
     * @throws ItemNotFoundException
     */
    @Override
    public synchronized final void update(T t) throws ItemNotFoundException {
        try {
            items = deserialize();
            boolean remove = this.items.remove(t);
            if (!remove) {
                throw new ItemNotFoundException("Item não encontrado");
            }
            this.items.add(t);
            serialize(items);
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Remove um objeto.
     *
     * @param t
     * @throws ItemNotFoundException
     */
    @Override
    public synchronized final void delete(T t) throws ItemNotFoundException {
        try {
            items = deserialize();
            boolean remove = this.items.remove(t);
            if (!remove) {
                throw new ItemNotFoundException("Item não encontrado");
            }
            serialize(items);
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}