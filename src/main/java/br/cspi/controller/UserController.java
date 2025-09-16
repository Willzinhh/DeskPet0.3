package br.cspi.controller;


import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.User;
import br.cspi.service.OwnerService;
import br.cspi.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService UserService;
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/listar")
    public List<User> listar() {
        return UserService.listar();
    }

    @GetMapping("/{id}")
    public User buscar(@PathVariable long id) {
        return this.UserService.getUser(id);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        this.UserService.salvar(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @DeleteMapping
    public ResponseEntity deletar(@PathVariable long id) {
        this.UserService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
