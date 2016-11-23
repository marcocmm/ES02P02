/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.DAO.Serviços;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class SicronizaDadosTest {

    public SicronizaDadosTest() {
    }

    @Test
    public void testSomeMethod() {
        SincronizaDados sincronizaDados = new SincronizaDados();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 2000);

    }

}
