/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.db;

import clube.socios.controlesociosclube.model.entidades.Socio;

/**
 *
 * @author romulo
 */
public class SocioDAO extends GenericDAO<Socio> {

    public SocioDAO() {
        super(Socio.class);
    }

    public Socio obter(String cpf) {
        super.entityManager.clear();
        return (Socio) entityManager.find(clazz, cpf);
    }

}
