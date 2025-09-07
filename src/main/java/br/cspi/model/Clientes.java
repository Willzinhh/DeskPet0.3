package br.cspi.model;

import java.sql.Timestamp;

public class Clientes {
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;
    private Timestamp data_criacao;
    private int cliente_usuario_id;

    // GET & SET  //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Timestamp getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Timestamp data_criacao) {
        this.data_criacao = data_criacao;
    }

    public int getCliente_usuario_id() {
        return cliente_usuario_id;
    }

    public void setCliente_usuario_id(int cliente_usuario_id) {
        this.cliente_usuario_id = cliente_usuario_id;
    }


    /// //////////////////////////// ///
}
