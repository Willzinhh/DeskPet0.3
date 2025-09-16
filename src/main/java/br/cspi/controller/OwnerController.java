package br.cspi.controller;


import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.User;
import br.cspi.service.OwnerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/owner")
public class OwnerController {

    private OwnerService OwnerService;
    public OwnerController(OwnerService OwnerService) {
        this.OwnerService = OwnerService;
    }

    @GetMapping("/listar")
    public List<Owner> listar() {
        return OwnerService.listar();
    }

    @GetMapping("/{id}")
    public Owner buscar(@PathVariable long id) {
        return this.OwnerService.getOwner(id);
    }

    @PutMapping
    public Owner atualizar(@RequestBody @Valid Owner owner ) {
        return this.OwnerService.salvar(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable long id) {
        this.OwnerService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/addUser")
    @Transactional
    public ResponseEntity addUser(@PathVariable long id, @RequestBody @Valid User user) {
        return ResponseEntity.ok(this.OwnerService.atribuirUser(id,user));
    }



}
