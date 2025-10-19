package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.cliente.DadosCliente;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;


    public DadosCliente salvar(long owner_id, Clientes cliente) {
        Owner owner = ownerRepository.getReferenceById(owner_id);
        cliente.setOwner(owner);
        Clientes c = this.repository.save(cliente);
        return new DadosCliente(c);
    }

    public List<DadosCliente> listar(long owner_id) {
        List<DadosCliente> dadosCliente = repository.findClientesByOwner(owner_id).stream().map(DadosCliente::new).toList();
        if (dadosCliente.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return dadosCliente;
    }

    public DadosCliente getCliente(long owner_id, long id) {
        Clientes cliente = repository.findClienteByOwnerAndId(owner_id, id);
        if (cliente == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return new DadosCliente(cliente);
    }

    public void excluir(long owner_id ,long id) {

        if (this.repository.findClienteByOwnerAndId(owner_id,id) == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        this.repository.deleteById(id);

    }

    public DadosCliente editar(Clientes cliente) {
        Clientes c = this.repository.getReferenceById(cliente.getId());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setTelefone(cliente.getTelefone());
        c.setEndereco(cliente.getEndereco());

        this.repository.save(c);
        return new DadosCliente(c);
    }
    public String atribuirPet(long owner_id, long id, Pet pet) {
        Clientes cliente = this.repository.findClienteByOwnerAndId(owner_id, id);
        if (cliente == null ) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        pet.setTutor(cliente);
        pet.setOwner(cliente.getOwner());
        petRepository.save(pet);
        cliente.addPet(pet);
        return "Pet Atribuido com sucesso ";
    }

}


