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
    private String nascimento;
    private Sexo sexo;
    private final Collection<Dependente> dependentes;
    private final Collection<Unidade> unidades;
    private final Collection<Mensalidade> mensalidades;
    private Collection<Atividade> atividades;
    private final Collection<Checkin> checkins;

    protected Socio() {
        this.cpf = null;
        this.dependentes = null;
        this.unidades = null;
        this.mensalidades = null;
        this.atividades = null;
        this.checkins = null;
    }

    public Socio(String cpf, String nome, String nascimento, Sexo sexo, Collection<Unidade> unidades, Collection<Atividade> atividades) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.unidades = unidades;
        this.dependentes = new ArrayList<>();
        this.mensalidades = new ArrayList<>();
        this.atividades = atividades;
        this.checkins = new ArrayList<>();
    }

    public Socio(String cpf, String nome, String nascimento, Sexo sexo, Collection<Dependente> dependentes, int unidades, Collection<Mensalidade> mensalidades, Collection<Atividade> atividades) {
        this.idSocio = 1;
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.dependentes = dependentes;
        this.unidades = new ArrayList<>();
        this.mensalidades = mensalidades;
        this.atividades = atividades;
        this.checkins = new ArrayList<>();
    }

    public Collection<Atividade> getAtividades() {
        return atividades;
    }

    public void addAtividade(Atividade atividade) {
        if (!this.atividades.contains(atividade)) {
            this.atividades.add(atividade);
        }
    }

    public void setAtividades(Collection<Atividade> atividades) {
        this.atividades = atividades;
    }

    public void addCheckin(Checkin checkin) {
        this.checkins.add(checkin);
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

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void addDependente(Dependente dependente) {
        if (this.dependentes.contains(dependente)) {
            this.dependentes.remove(dependente);
        }
        this.dependentes.add(dependente);
    }

    public void removeDependente(Dependente dependete) {
        this.dependentes.remove(dependete);
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

    public void addMensalidade(Mensalidade mensalidade) {
        this.mensalidades.add(mensalidade);
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
