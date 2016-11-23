/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.BO;

import clube.socios.controlesociosclube.model.entidades.Socio;
import clube.socios.controlesociosclube.model.view.CRUDSocio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class SocioBOTest {

    public SocioBOTest() {
    }

    @Test
    public void testSomeMethod() {
        try {
            String cpf = "123456";
            Socio obterSocio = SocioBO.obterSocio(cpf);
        } catch (ItemNotFoundException ex) {
            Logger.getLogger(CRUDSocio.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getGlobal().log(Level.INFO, "item not found");
        } catch (ItemNotFoundHereException ex) {
            Logger.getGlobal().log(Level.SEVERE, "item nao encontrado");
        }
    }

}
