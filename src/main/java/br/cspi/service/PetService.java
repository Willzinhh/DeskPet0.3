package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.DadosPetInput;
import br.cspi.model.pet.DadosPetOutput;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@ControllerAdvice
public class PetService {

    private final PetRepository repository;
    private final ClienteRepository clienteRepository;

    public DadosPetInput salvar(Pet pet) {

        this.repository.save(pet);

        return new DadosPetInput(pet);
    }

    public List<DadosPetOutput> listar(long owner_id) {
        List<DadosPetOutput> dp = this.repository.findPetsByOwner(owner_id).stream().map(DadosPetOutput::new).toList();
        if (dp.isEmpty()) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return dp;
    }

    public List<DadosPetOutput> listarByTutor(long owner_id, long tutor_id) {
        List<DadosPetOutput> dp = this.repository.findPetByOwnerAndTutor(owner_id, tutor_id).stream().map(DadosPetOutput::new).toList();
        if (dp.isEmpty()) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return dp;
    }

    public DadosPetOutput getPet(long owner_id, long id) {
        Pet pet = this.repository.findPetByOwnerAndId(owner_id, id);

        if (pet == null) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        return new DadosPetOutput(pet);
    }

    public void excluir(long owner_id, long id) {
        Pet pet = this.repository.findPetByOwnerAndId(owner_id, id);
        if (pet == null) {
            throw new NoSuchElementException("Pet não encontrado");
        }
        this.repository.deleteById(id);
    }

    public DadosPetOutput editar(long owner_id, @Valid Pet pet) {



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
        return new DadosPetOutput(pet);
    }
}
