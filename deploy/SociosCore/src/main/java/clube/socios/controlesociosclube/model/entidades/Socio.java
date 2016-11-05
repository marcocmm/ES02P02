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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author romulo
 */
@Entity
public class Socio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSocio;
    private String nome;
    private final String cpf;
    private int idade;
    private Sexo sexo;
    private final Collection<Dependente> dependentes;
    private final Collection<Unidade> unidades;
    private final Collection<Mensalidade> mensalidades;
    private final Collection<Atividade> atividades;

    protected Socio() {
        this.cpf = null;
        this.dependentes = null;
        this.unidades = null;
        this.mensalidades = null;
        this.atividades = null;
    }

    public Socio(String cpf, String nome, int idade, Sexo sexo, Collection<Unidade> unidades, Collection<Atividade> atividades) {
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

    public String getCpf() {
        return cpf;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Socio)) {
            return false;
        }
        Socio socio = (Socio) obj;
        return this.cpf.equals(socio.cpf);
    }

}
