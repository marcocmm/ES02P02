/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.db;

import clube.socios.controlesociosclube.model.entidades.Sexo;
import clube.socios.controlesociosclube.model.entidades.Socio;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class SocioDAOTest {

    public SocioDAOTest() {
    }

    @Test
    public void testSomeMethod() {
        SocioDAO socioDAO = new SocioDAO();
        Socio socio = new Socio("87", "teste", 0, Sexo.FEMININO, null, null);
        socioDAO.insert(socio);
    }

}
