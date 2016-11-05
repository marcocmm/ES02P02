/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.entidades;

import java.util.Date;

/**
 *
 * @author romulo
 */
public class Mensalidade {

    private int idMensalidade;
    private Date dataVencimento;
    private Boolean paga;
    private double valorMensalidade;

    public int getIdMensalidade() {
        return idMensalidade;
    }

    public void setData(Date data) {
        this.dataVencimento = data;
    }

    public void setPaga(Boolean paga) {
        this.paga = paga;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public Date getData() {
        return dataVencimento;
    }

    public Boolean isPaga() {
        return paga;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

}
