/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.entidades;

/**
 *
 * @author romulo
 */
public class Dependente {

    private String nome;
    private int idade;
    private Sexo sexo;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

}
