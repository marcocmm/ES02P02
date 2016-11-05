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
 *
 * @author romulo
 * @param <T>
 */
public class SerializePersistence<T extends Serializable> implements Persistence<T> {

    private Collection<T> items;
    private static final String DB_URL = "/data/documents/workspace/ES02P02/deploy/database";

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

    private synchronized void serialize(Collection<T> items) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DB_URL);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(items);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private synchronized Collection<T> deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(DB_URL);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Collection<T> items = (Collection<T>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return items;
    }

    @Override
    public synchronized Collection<T> list() {
        try {
            items = deserialize();
        } catch (IOException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    @Override
    public synchronized void create(T t) {
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

    @Override
    public synchronized T retrieve(T t) throws ItemNotFoundException {
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

    @Override
    public synchronized void update(T t) throws ItemNotFoundException {
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

    @Override
    public synchronized void delete(T t) throws ItemNotFoundException {
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
