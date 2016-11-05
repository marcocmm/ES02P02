/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

import java.util.Collection;

/**
 *
 * @author RômuloManciola
 * @param <T>
 */
public interface Persistence<T> {

    public Collection<T> list();

    public void create(T t);

    public T retrieve(T t) throws ItemNotFoundException;

    public void update(T t) throws ItemNotFoundException;

    public void delete(T t) throws ItemNotFoundException;
}
