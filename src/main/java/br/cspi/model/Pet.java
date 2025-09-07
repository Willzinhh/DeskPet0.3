package br.cspi.model;

import java.util.Date;

public class Pet {

    private int id;
    private String nomepet;
    private String especie;
    private String raca;
    private String sexo;
    private String descricao;
    private Date data_cricao;
    private int tutor_id;
    private int cliente_usuario_id;

    // GET & SET  //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomepet() {
        return nomepet;
    }

    public void setNomepet(String nomepet) {
        this.nomepet = nomepet;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_cricao() {
        return data_cricao;
    }

    public void setData_cricao(Date data_cricao) {
        this.data_cricao = data_cricao;
    }

    public int getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }

    public int getCliente_usuario_id() {
        return cliente_usuario_id;
    }

    public void setCliente_usuario_id(int cliente_usuario_id) {
        this.cliente_usuario_id = cliente_usuario_id;
    }

    /// //////////////////////////// ///
}
