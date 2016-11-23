/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.entidades;

import java.io.Serializable;

/**
 *
 * @author romulo
 */
public class Unidade implements Serializable {

    private int idUnidade;
    private String nome;
    private String cidade;

    public Unidade(String nome, String cidade) {
        this.idUnidade = 0;
        this.nome = nome;
        this.cidade = cidade;
    }

}
