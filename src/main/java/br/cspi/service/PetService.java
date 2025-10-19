package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.DadosPet;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import br.cspi.model.usuario.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository repository;
    private final ClienteRepository clienteRepository;

    public DadosPet salvar(Pet pet) {

        this.repository.save(pet);

        return new DadosPet(pet);
    }

    public List<DadosPet> listar(long owner_id) {
        List<DadosPet> dp = this.repository.findPetsByOwner(owner_id).stream().map(DadosPet::new).toList();
        if (dp.isEmpty()) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return dp;
    }

    public List<DadosPet> listarByTutor(long owner_id, long tutor_id) {
        List<DadosPet> dp = this.repository.findPetByOwnerAndTutor(owner_id, tutor_id).stream().map(DadosPet::new).toList();
        if (dp.isEmpty()) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return dp;
    }

    public DadosPet getPet(long owner_id, long id) {
        Pet pet = this.repository.findPetByOwnerAndId(owner_id, id);

        if (pet == null) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return new DadosPet(pet);
    }

    public void excluir(long owner_id, long id) {
        Pet pet = this.repository.findPetByOwnerAndId(owner_id, id);
        if (pet == null) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        this.repository.deleteById(id);
    }

    public DadosPet editar(long owner_id, Pet pet) {
        Pet p = this.repository.findPetByOwnerAndId(owner_id, pet.getId());
        if (p == null) {
            throw new NoSuchElementException("Pet não encontrado");
        }

        if (pet.getTutor() != p.getTutor() && pet.getTutor() != null) {
            Clientes lc = this.clienteRepository.findClienteByOwnerAndId(owner_id, p.getId());
            lc.removePet(p);
            Clientes nc = this.clienteRepository.findClienteByOwnerAndId(owner_id, p.getId());
            nc.addPet(pet);
        }

        pet.setTutor(p.getTutor());
        pet.setOwner(p.getOwner());

        this.repository.save(pet);
        return new DadosPet(pet);
    }
}
