package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.usuario.Owner;
import br.cspi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    private ClienteService  ClienteService;
    public ClienteController(ClienteService ClienteService) {
        this.ClienteService = ClienteService  ;
    }

    @GetMapping("/listar")
    public List<Clientes> listar() {
        return ClienteService.listar();
    }

    @GetMapping("/{id}")
    public Clientes buscar(@PathVariable long id) {
        return this.ClienteService .getCliente(id);
    }

    @PutMapping
    public Clientes atualizar(@RequestBody @Valid Clientes cliente ) {
        return this.ClienteService .salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable long id) {
        this.ClienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
