/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.db;

import clube.socios.controlesociosclube.model.entidades.Socio;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * DAO especifico para a tabela Socio.
 *
 * @author romulo
 */
public class SocioDAO extends GenericDAO<Socio> {

    public SocioDAO() {
        super(Socio.class);
    }

    public Socio obter(String cpf) throws NoResultException {
        Query createQuery = entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.cpf = :cpf");
        createQuery.setParameter("cpf", cpf);
        return (Socio) createQuery.getSingleResult();
    }
    
    public Socio obterPeloNome(String nome) throws NoResultException {
        Query createQuery = entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.nome = :nome");
        createQuery.setParameter("nome", nome);
        return (Socio) createQuery.getSingleResult();
    }

}
