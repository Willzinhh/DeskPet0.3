package br.cspi.model.cliente;

import br.cspi.model.pet.DadosPetInput;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.List;

public record DadosClienteInput(
                                @Schema(description = "ID do Cliente", hidden = true )
                                long id,
                                String nome,
                                String telefone,
                                String cpf,
                                String endereco,
                                Timestamp data_criacao,

                                @Schema(description = "ID do Proprietario", hidden = true )
                           Long owner_id,

                                @Schema(description = "Lista de Pets", hidden = true )
                           List<DadosPetInput> pets) {

    public DadosClienteInput(Clientes cliente){
         this(cliente.getId(), cliente.getNome(),cliente.getTelefone(), cliente.getCpf(),
                cliente.getEndereco(), cliente.getData_criacao(), cliente.getOwner().getId(), cliente.getPets().stream().map(DadosPetInput::new).toList());
    }

}
