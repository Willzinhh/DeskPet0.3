package br.cspi.model.servico;

import br.cspi.model.funcionario.Funcionario;

import java.util.List;
import java.util.Set;

public record DadosServico(Long id, String nome, String descricao , double valor , String tempo, Long owner_id, Set<Funcionario> funcionarios) {
    public DadosServico(Servico s) {
        this((long) s.getId(), s.getNome(), s.getDescricao(), s.getValor(), s.getTempo(),s.getOwner().getId(), s.getFuncionarios());
    }
}
