package br.cspi.model.cliente;

import br.cspi.model.pet.DadosPetInput;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.List;

//public record DadosClienteOutput(
//        Long id, String nome,
//        String telefone,
//        String cpf,
//        String endereco,
//        Timestamp data_criacao,
//
//        @Schema(description = "ID do Proprietario", hidden = true )
//                                Long owner_id,
//
//        @Schema(description = "Lista de Pets", hidden = true )
//                                List<DadosPet> pets) {
//
//    public DadosClienteOutput(Clientes cliente){
//        this(cliente.getId(), cliente.getNome(),cliente.getTelefone(), cliente.getCpf(),
//                cliente.getEndereco(), cliente.getData_criacao(), cliente.getOwner().getId(), cliente.getPets().stream().map(DadosPet::new).toList());
//    }
//
//
//}


/**
 * DTO de saída que representa as informações detalhadas de um cliente.
 */
public record DadosClienteOutput(

        @Schema(description = "Identificador único do cliente.", example = "5")
        Long id,

        @Schema(description = "Nome completo do cliente.", example = "Maria Oliveira")
        String nome,

        @Schema(description = "Número de telefone para contato.", example = "(21)99887-7665")
        String telefone,

        @Schema(description = "CPF (Cadastro de Pessoa Física) do cliente.", example = "987.654.321-11")
        String cpf,

        @Schema(description = "Endereço completo do cliente.", example = "Av. Principal, 456 - Bairro X")
        String endereco,

        @Schema(description = "Data e hora de criação do registro.", example = "2025-10-31T15:40:16.978Z")
        Timestamp data_cricao)
{

    /**
     * Construtor que recebe a entidade Clientes e mapeia seus dados para o DTO.
     * * @param cliente A entidade Clientes a ser mapeada.
     */
    public DadosClienteOutput(Clientes cliente){
        this(cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getData_criacao());
    }
}
