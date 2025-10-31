package br.cspi.model.cliente;

import br.cspi.model.pet.DadosPetInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.List;

public record DadosClienteInput(

        @Schema(description = "Identificador único do cliente.", example = "5", hidden = true)
        Long id,

        @NotBlank
        @Schema(description = "Nome completo do cliente.", example = "Maria Oliveira")
        String nome,

        @Size(min = 14, max = 14, message = "Telefone deve seguir o formato (XX)XXXXX-XXXX")
        @Schema(description = "Número de telefone para contato.", example = "(21)99887-7665")
        String telefone,

        @Size(min = 14, max = 14, message = "CPF deve seguir o formato XXX.XXX.XXX-XX ")
        @Schema(description = "CPF (Cadastro de Pessoa Física) do cliente.", example = "987.654.321-11")
        String cpf,

        @NotBlank
        @Schema(description = "Endereço completo do cliente.", example = "Av. Principal, 456 - Bairro X")
        String endereco,


        @Schema(description = "Data e hora de criação do registro.", example = "2025-10-31T15:40:16.978Z")
        Timestamp data_criacao ){


        public DadosClienteInput(Clientes cliente){
         this(cliente.getId(), cliente.getNome(),cliente.getTelefone(), cliente.getCpf(),
                cliente.getEndereco(), cliente.getData_criacao());
    }

}
