package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;


    public PetService(PetRepository repository) {
        this.repository = repository;
    }


    public Pet salvar(Pet pet) {this.repository.save(pet);

        return pet;
    }

    public List<Pet> listar() {return this.repository.findAll();}

    public Pet getPet(Long id) {return this.repository.findById(id).get();}

    public void excluir(Long id) {this.repository.deleteById(id);}

    public void editar(Pet pet) {
        Pet p = this.repository.getReferenceById( pet.getId());


        this.repository.save(p);
    }
}
