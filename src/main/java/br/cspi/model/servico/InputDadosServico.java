package br.cspi.model.servico;

public record InputDadosServico(Long id, String nome, String descricao , double valor , String tempo, Long owner_id) {
}
