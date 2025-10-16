package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.usuario.Owner;
import br.cspi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de Clientes (Tutores).")
public class ClienteController {


    private ClienteService  ClienteService;
    public ClienteController(ClienteService ClienteService) {
        this.ClienteService = ClienteService  ;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Clientes", description = "Lista todos os Clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Clientes.class))),
    })
    public List<Clientes> listar() {
        return ClienteService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente por ID", description = "Retorna Cliente correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Clientes.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content) // Alterei o 201 para 200 e adicionei 404

    })
    public Clientes buscar(@Parameter(description = "ID do Cliente a ser buscado") @PathVariable long id) {
        return this.ClienteService .getCliente(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo Cliente", description = "Criar um novo Cliente e adicionar à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Clientes.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content)

    })
    public void salvar(@RequestBody @Valid Clientes cliente) {this.ClienteService.salvar(cliente);}

    @PutMapping
    @Operation(summary = "Atualizar Cliente", description = "Atualiza um Cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Clientes.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)

    })
    public Clientes atualizar(@RequestBody @Valid Clientes cliente ) {
        return this.ClienteService .salvar(cliente);
    }

    @DeleteMapping("/{id}")@Operation(summary = "Deletar Cliente por ID", description = "Remove o Cliente correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Cliente a ser deletado")@PathVariable long id) {
        this.ClienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
