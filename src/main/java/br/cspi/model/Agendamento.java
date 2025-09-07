package br.cspi.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Agendamento {
    private int id;
    private Date data;
    private Time horario;
    private int pet_id;
    private int servico_id;
    private int funcionario_id;
    private String observacao;
    private String status;
    private String pagamento;
    private int cliente_usuario_id;

    // campos extras (opcional)
    private String petNome;
    private String servicoNome;
    private String funcionarioNome;

    // ⚠️ Adicione esse método:
    public String getDiaSemana() {
        LocalDate localDate = data.toLocalDate();
        return localDate.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))
                .toUpperCase(); // Ex: "SEGUNDA-FEIRA"
    }

    // Getters e Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getServico_id() {
        return servico_id;
    }

    public void setServico_id(int servico_id) {
        this.servico_id = servico_id;
    }

    public int getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(int funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public int getCliente_usuario_id() {
        return cliente_usuario_id;
    }

    public void setCliente_usuario_id(int cliente_usuario_id) {
        this.cliente_usuario_id = cliente_usuario_id;
    }

    public String getStatus_pagamento() {
        return this.pagamento;
    }

    public void setStatus_pagamento(String statusPagamento) {
        this.pagamento = statusPagamento;
    }

    public String getPetNome() {
        return petNome;
    }

    public void setPetNome(String petNome) {
        this.petNome = petNome;
    }

    public String getServicoNome() {
        return servicoNome;
    }

    public void setServicoNome(String servicoNome) {
        this.servicoNome = servicoNome;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }
}

