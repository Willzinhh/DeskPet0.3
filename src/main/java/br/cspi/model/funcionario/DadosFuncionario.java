package br.cspi.model.funcionario;

import java.math.BigDecimal;

public record DadosFuncionario(long id, String nome, String cpf, String telefone, String cargo,  boolean ativo, long owner_id) {
        public DadosFuncionario(Funcionario f) {this(f.getId(), f.getNome(), f.getCpf(), f.getTelefone(), f.getCargo(),  f.isAtivo(), f.getOwner().getId());}


}
