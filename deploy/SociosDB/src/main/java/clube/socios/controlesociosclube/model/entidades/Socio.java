/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clube.socios.controlesociosclube.model.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author romulo
 */
@Entity
public class Socio implements Serializable {

    @Id
    private final int idSocio;
    private String nome;
    private final String cpf;
    private int idade;
    private Sexo sexo;
    private final Collection<Dependente> dependentes;
    private final Collection<Unidade> unidades;
    private final Collection<Mensalidade> mensalidades;
    private final Collection<Atividade> atividades;

    protected Socio() {
        this.idSocio = 0;
        this.cpf = null;
        this.dependentes = null;
        this.unidades = null;
        this.mensalidades = null;
        this.atividades = null;
    }

    public Socio(String cpf, String nome, int idade, Sexo sexo, Collection<Unidade> unidades, Collection<Atividade> atividades) {
        this.idSocio = 1;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.unidades = unidades;
        this.dependentes = new ArrayList<>();
        this.mensalidades = new ArrayList<>();
        this.atividades = atividades;
    }

    public Socio(String cpf, String nome, int idade, Sexo sexo, Collection<Dependente> dependentes, int unidades, Collection<Mensalidade> mensalidades, Collection<Atividade> atividades) {
        this.idSocio = 1;
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
