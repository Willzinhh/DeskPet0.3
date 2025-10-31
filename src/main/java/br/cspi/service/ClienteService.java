package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.cliente.DadosClienteOutput;
import br.cspi.model.pet.DadosPetInput;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClienteService {


    private final ClienteRepository repository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;


    public DadosClienteOutput salvar(long owner_id, Clientes cliente) {
        Owner owner = ownerRepository.getReferenceById(owner_id);
        cliente.setOwner(owner);
        Clientes c = this.repository.save(cliente);
        return new DadosClienteOutput(c);
    }

    public List<DadosClienteOutput> listar(long owner_id) {
        List<DadosClienteOutput> dadosClienteOutputs = repository.findClientesByOwner(owner_id).stream().map(DadosClienteOutput::new).toList();
        if (dadosClienteOutputs.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return dadosClienteOutputs;
    }

    public DadosClienteOutput getCliente(long owner_id, long id) {
        Clientes cliente = repository.findClienteByOwnerAndId(owner_id, id);
        if (cliente == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return new DadosClienteOutput(cliente);
    }

    public void excluir(long owner_id ,long id) {

        if (this.repository.findClienteByOwnerAndId(owner_id,id) == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        this.repository.deleteById(id);

    }

    public DadosClienteOutput editar(@Valid Clientes cliente) {
        Clientes c = this.repository.getReferenceById(cliente.getId());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setTelefone(cliente.getTelefone());
        c.setEndereco(cliente.getEndereco());

        this.repository.save(c);
        return new DadosClienteOutput(c);
    }
    public String atribuirPet(long owner_id, long id, @Valid DadosPetInput pet) {
        Clientes cliente = this.repository.findClienteByOwnerAndId(owner_id, id);
        if (cliente == null ) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        Pet petEntity = new Pet();

        petEntity.setNomepet(pet.nomepet());
        petEntity.setEspecie(pet.especie());
        petEntity.setRaca(pet.raca());
        petEntity.setSexo(pet.sexo());
        petEntity.setDescricao(pet.descricao());
        petEntity.setData_cricao(pet.data_criacao());
        petEntity.setOwner(cliente.getOwner());
        petEntity.setTutor(cliente);
        petRepository.save(petEntity);
        cliente.addPet(petEntity);
        return "Pet Atribuido com sucesso ";
    }

}


