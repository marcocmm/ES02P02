/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

import java.util.Collection;

/**
 * Interface que define o comportamento de uma entidade de persistencia numa
 * base de dados.
 *
 * @author RômuloManciola
 * @param <T>
 */
public interface Persistence<T> {

    public Collection<T> list();

    public void create(T t);

    /**
     * Obtem um item.
     *
     * @param t Item a ser buscado. A chave primaria devera constar no metodo
     * equals.
     * @return Retorna o item.
     * @throws ItemNotFoundException Caso o item nao seja encontrado, propaga
     * exceçao.
     */
    public T retrieve(T t) throws ItemNotFoundException;

    public void update(T t) throws ItemNotFoundException;

    public void delete(T t) throws ItemNotFoundException;
}
