package br.cspi.model.funcionario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record InputDadosFuncionario(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "Identificador único do Funcionário.", example = "10")
        long id,

        @NotBlank
        @Schema(description = "Nome completo do funcionário.", example = "João da Silva")
        String nome,

        @Size(min = 14, max = 14, message = "CPF deve seguir o formato XXX.XXX.XXX.XX")
        @Schema(description = "CPF (Cadastro de Pessoa Física) do funcionário.", example = "123.456.789-00")
        String cpf,

        @Size(min = 14, max = 14, message = "Telefone deve seguir o formato (XX)XXXXX-XXXX")
        @Schema(description = "Número de telefone para contato.", example = "(11)98765-4321")
        String telefone,

        @NotBlank
        @Schema(description = "Cargo ou função do funcionário.", example = "Veterinário")
        String cargo,

        @NotNull
        @Positive
        @Schema(description = "Salario do funcionário.", example = "1500")
        BigDecimal salario,

        @Schema(description = "Status de atividade do funcionário (true para ativo, false para inativo).", example = "true")
        boolean ativo,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        long owner_id){

}
