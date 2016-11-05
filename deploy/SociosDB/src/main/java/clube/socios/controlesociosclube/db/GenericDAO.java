/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.db;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author romulo
 */
public class GenericDAO<T> {

    final EntityManager entityManager;
    final Class clazz;

    public GenericDAO(Class clazz) {
        entityManager = Persistence.createEntityManagerFactory("SociosPU").createEntityManager();
        this.clazz = clazz;
    }

    public boolean insert(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return true;
    }

    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        if (entity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
    }

    public T obter(int id) {
        entityManager.clear();
        return (T) entityManager.find(clazz, id);
    }

    public T obter(T entity) {
        entityManager.clear();
        return (T) entityManager.find(clazz, entity);
    }

    public Collection<T> list() {
        return entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }
}
