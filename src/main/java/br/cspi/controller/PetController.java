package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.Pet;
import br.cspi.service.ClienteService;
import br.cspi.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {


    private PetService PetService;
    public PetController(PetService PetService) {
        this.PetService = PetService;
    }

    @GetMapping("/listar")
    public List<Pet> listar() {
        return PetService.listar();
    }

    @GetMapping("/{id}")
    public Pet buscar(@PathVariable long id) {
        return this.PetService .getPet(id);
    }

    @PutMapping
    public Pet atualizar(@RequestBody @Valid Pet cliente ) {
        return this.PetService .salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable long id) {
        this.PetService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
