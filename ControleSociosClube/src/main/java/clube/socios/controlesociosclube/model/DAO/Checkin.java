/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.DAO;

import java.io.Serializable;

/**
 *
 * @author romulo
 */
public class Checkin implements Serializable {

    private int quantidadeDeCheckin;

    public void addQuantidadeDeCheckin() {
        quantidadeDeCheckin++;
    }

    public int getQuantidadeDeCheckin() {
        return quantidadeDeCheckin;
    }

}
