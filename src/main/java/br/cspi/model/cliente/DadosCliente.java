package br.cspi.model.cliente;

import br.cspi.model.pet.DadosPet;
import br.cspi.model.pet.Pet;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record DadosCliente(long id, String nome, String telefone, String cpf, String endereco, Timestamp data_criacao, Long owner_id, List<DadosPet> pets) {
    public DadosCliente(Clientes cliente){
         this(cliente.getId(), cliente.getNome(),cliente.getTelefone(), cliente.getCpf(),
                cliente.getEndereco(), cliente.getData_criacao(), cliente.getOwner().getId(), cliente.getPets().stream().map(DadosPet::new).toList());
    }

}
