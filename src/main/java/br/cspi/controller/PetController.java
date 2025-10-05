package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.Pet;
import br.cspi.service.ClienteService;
import br.cspi.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso", content = @Content(mediaType = "application/json",
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


}
