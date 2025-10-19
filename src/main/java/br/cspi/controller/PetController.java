package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.pet.DadosPet;
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
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pet")
@AllArgsConstructor
@Tag(name = "Pet", description = "Endpoints para gerenciamento de Pets.")

public class PetController {


    private PetService PetService;

    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Pets", description = "Lista todos os Pets cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
    })
    public List<DadosPet> listar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id) {
        return PetService.listar(owner_id);
    }

    @GetMapping("/listar/{owner_id}/{tutor_id}")
    @Operation(summary = "Listar Pets", description = "Lista todos os Pets cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
    })
    public List<DadosPet> listarByTutor(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @Parameter(description = "ID do Proprietario") @PathVariable long tutor_id) {
        return PetService.listarByTutor(owner_id, tutor_id);
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Pet por ID", description = "Retorna Pet correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    public DadosPet buscar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @Parameter(description = "ID do Pet a ser buscado") @PathVariable long id) {
        return this.PetService .getPet(owner_id, id);
    }

    @PutMapping("/{owner_id}")
    @Operation(summary = "Atualizar Pet", description = "Atualiza um Pet existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    public ResponseEntity<DadosPet> atualizar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,@RequestBody @Valid Pet pet, UriComponentsBuilder uriBuilder ) {
       DadosPet dp = this.PetService.editar(owner_id, pet);
       URI uri = uriBuilder.path("/pet/owner_id").buildAndExpand(pet.getId()).toUri();
         return ResponseEntity.created(uri).body(dp);
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Pet por ID", description = "Remove o Pet correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Pet a ser deletado")@PathVariable long owner_id, @Parameter(description = "ID do Pet a ser deletado")@PathVariable long id) {
        this.PetService.excluir(owner_id, id);
        return ResponseEntity.noContent().build();
    }




}
