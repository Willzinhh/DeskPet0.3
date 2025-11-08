//package br.cspi.model.funcionario;
//
//public record DadosFuncionarioOutput(
//        long id,
//        String nome, String cpf, String telefone, String cargo, boolean ativo, long owner_id) {
//        public DadosFuncionarioOutput(Funcionario f) {this(f.getId(), f.getNome(), f.getCpf(), f.getTelefone(), f.getCargo(),  f.isAtivo(), f.getOwner().getId());}
//
//
//}

package br.cspi.model.funcionario;

import br.cspi.model.servico.Servico;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO de saída que representa as informações de um Funcionário.
 */
public record DadosFuncionarioOutput(

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
        boolean ativo



) {



        public DadosFuncionarioOutput(Funcionario f) {
                this(f.getId(),
                        f.getNome(),
                        f.getCpf(),
                        f.getTelefone(),
                        f.getCargo(),
                        f.getSalario(),
                        f.isAtivo()
                        ); // Assumindo que f.getOwner() retorna um objeto que tem o método getId()
        }
}
