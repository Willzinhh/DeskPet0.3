package br.cspi.model.funcionario;

import java.math.BigDecimal;
import java.util.List;

public record InputDadosFuncionario(long id, String nome, String cpf, String telefone, String cargo, BigDecimal salario, boolean ativo, long owner_id, List<Long> servico_id) {

}
