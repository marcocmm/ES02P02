/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.entidades;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author romulo
 */
public class Socio {

    private final int idSocio;
    private String nome;
    private final String cpf;
    private int idade;
    private Sexo sexo;
    private final Collection<Dependente> dependentes;
    private final Collection<Unidade> unidades;
    private final Collection<Mensalidade> mensalidades;
    private final Collection<Atividade> atividades;

    public Socio(String nome, int idade, Sexo sexo, Collection<Unidade> unidades, Collection<Mensalidade> mensalidades, Collection<Atividade> atividades) {
        this.idSocio = idSocio;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dependentes = new ArrayList<>();
        this.unidades = unidades;
        this.mensalidades = mensalidades;
        this.atividades = atividades;
    }

    public Socio(String nome, int idade, Sexo sexo, Collection<Dependente> dependentes, int unidades, Collection<Mensalidade> mensalidades, Collection<Atividade> atividades) {
        this.idSocio = idSocio;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.dependentes = dependentes;
        this.unidades = new ArrayList<>();
        this.mensalidades = mensalidades;
        this.atividades = atividades;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdSocio() {
        return idSocio;
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

    setdependente ;
    setunidade ;
    setmensalidade ;

    public Collection<Dependente> getDependentes() {
        return dependentes;
    }

    public Collection<Unidade> getUnidades() {
        return unidades;
    }

    public Collection<Mensalidade> getMensalidades() {
        return mensalidades;
    }

}
