package br.cspi.controller;

import br.cspi.model.pet.DadosPetInput;
import br.cspi.model.pet.DadosPetOutput;
import br.cspi.model.pet.Pet;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@AllArgsConstructor
@ControllerAdvice
@Tag(name = "Pet", description = "Endpoints para gerenciamento de Pets.")

public class PetController {


    private PetService PetService;

    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Pets por Proprietario (Owner)", description = "Lista todos os Pets cadastrados por Proprietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosPetOutput.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)
    })
    public List<DadosPetOutput> listar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id) {
        return PetService.listar(owner_id);
    }

    @GetMapping("/listar/{owner_id}/{tutor_id}")
    @Operation(summary = "Listar Pets Por Cliente(Tutor)", description = "Lista todos os Pets cadastrados de um Tutor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosPetOutput.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)
    })
    public List<DadosPetOutput> listarByTutor(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @Parameter(description = "ID do Proprietario") @PathVariable long tutor_id) {
        return PetService.listarByTutor(owner_id, tutor_id);
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Pet por ID", description = "Retorna Pet correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosPetOutput.class))),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    public DadosPetOutput buscar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @Parameter(description = "ID do Pet a ser buscado") @PathVariable long id) {
        return this.PetService.getPet(owner_id, id);
    }

    @PutMapping("/{owner_id}")
    @Operation(summary = "Atualizar Pet", description = "Atualiza um Pet existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)

    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<DadosPetOutput> atualizar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @RequestBody @Valid Pet pet) {
        DadosPetOutput dp = this.PetService.editar(owner_id, pet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Pet por ID", description = "Remove o Pet correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet excluído com sucesso",content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity deletar(@Parameter(description = "ID do Pet a ser deletado") @PathVariable long owner_id, @Parameter(description = "ID do Pet a ser deletado") @PathVariable long id) {
        this.PetService.excluir(owner_id, id);
        return ResponseEntity.noContent().build();
    }


}
