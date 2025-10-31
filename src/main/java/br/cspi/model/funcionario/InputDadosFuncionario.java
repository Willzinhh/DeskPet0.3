package br.cspi.model.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public record InputDadosFuncionario(
        @Schema(description = "Identificador único do Funcionário.", example = "10")
        long id,

        @Schema(description = "Nome completo do funcionário.", example = "João da Silva")
        String nome,

        @Schema(description = "CPF (Cadastro de Pessoa Física) do funcionário.", example = "123.456.789-00")
        String cpf,

        @Schema(description = "Número de telefone para contato.", example = "(11)98765-4321")
        String telefone,

        @Schema(description = "Cargo ou função do funcionário.", example = "Veterinário")
        String cargo,

        @Schema(description = "Salario do funcionário.", example = "1500")
        BigDecimal salario,

        @Schema(description = "Status de atividade do funcionário (true para ativo, false para inativo).", example = "true")
        boolean ativo,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        long owner_id){

}
