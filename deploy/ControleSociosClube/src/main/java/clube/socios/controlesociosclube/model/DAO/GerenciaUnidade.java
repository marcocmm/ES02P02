/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.DAO;

import clube.socios.controlesociosclube.model.entidades.Unidade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author romulo
 */
public class GerenciaUnidade implements Serializable {

    private final Collection<Checkin> checkins;
    private Unidade unidade;

    public GerenciaUnidade() {
        this.checkins = new ArrayList<>();
    }

    public boolean serializaCheckins(Collection<Checkin> checkins) {
        throw new UnsupportedOperationException();
    }
}
