package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.Pet;
import br.cspi.model.usuario.Owner;
import br.cspi.service.ClienteService;
import br.cspi.service.PetService;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Endpoints para gerenciamento de Pets.")

public class PetController {


    private PetService PetService;
    public PetController(PetService PetService) {
        this.PetService = PetService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Pets", description = "Lista todos os Pets cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
    })
    public List<Pet> listar() {
        return PetService.listar();
    }

    @GetMapping("/{id}") @Operation(summary = "Buscar Pet por ID", description = "Retorna Pet correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    public Pet buscar(@Parameter(description = "ID do Pet a ser buscado") @PathVariable long id) {
        return this.PetService .getPet(id);
    }

    @PutMapping
    @Operation(summary = "Atualizar Pet", description = "Atualiza um Pet existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    public Pet atualizar(@RequestBody @Valid Pet cliente ) {
        return this.PetService .salvar(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Pet por ID", description = "Remove o Pet correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Pet a ser deletado")@PathVariable long id) {
        this.PetService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Criar novo Pet", description = "Cria um novo Pet")
    @ApiResponses(value = {
            // Corrigido para 201 Created (POST) e 400 (Bad Request)
            @ApiResponse(responseCode = "201", description = "Pet criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Owner.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content)
    })
    public ResponseEntity salvar(@RequestBody @Valid Pet pet, UriComponentsBuilder uriBuilder) {
        this.PetService.salvar(pet);
        URI uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(pet);
    }


}
