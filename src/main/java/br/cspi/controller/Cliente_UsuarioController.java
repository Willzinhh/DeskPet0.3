package br.cspi.controller;

import br.cspi.model.usuario.Cliente_Usuario;
import br.cspi.service.Cliente_UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class Cliente_UsuarioController {

    private Cliente_UsuarioService cliente_usuarioService;
    public Cliente_UsuarioController(Cliente_UsuarioService cliente_usuarioService) {
        this.cliente_usuarioService = cliente_usuarioService;
    }

    @GetMapping("/listar")
    public List<Cliente_Usuario> listar() {
        return cliente_usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Cliente_Usuario buscar(@PathVariable long id) {
        return this.cliente_usuarioService.getClienteUsuario(id);
    }

    @PostMapping("/print-json")
    public void printJson(@RequestBody String json) {
        System.out.println(json);
    }

}
