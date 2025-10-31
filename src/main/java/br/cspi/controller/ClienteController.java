package br.cspi.controller;

import br.cspi.model.cliente.Clientes;
import br.cspi.model.cliente.DadosClienteInput;
import br.cspi.model.cliente.DadosClienteOutput;
import br.cspi.model.pet.DadosPetInput;
import br.cspi.model.pet.Pet;
import br.cspi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
@ControllerAdvice
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de Clientes (Tutores).")
public class ClienteController {


    private ClienteService ClienteService;

    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Clientes", description = "Lista todos os Clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosClienteOutput.class))),
    })
    public ResponseEntity<List<DadosClienteOutput>> listar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, UriComponentsBuilder uriBuilder) {
        List<DadosClienteOutput> dc =  this.ClienteService.listar(owner_id);
        URI uri = uriBuilder.path("/listar/{owner_id}").buildAndExpand(owner_id).toUri();
        return ResponseEntity.created(uri).body(dc);
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Cliente por ID", description = "Retorna Cliente correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosClienteOutput.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content) // Alterei o 201 para 200 e adicionei 404

    })
    public ResponseEntity<DadosClienteOutput> buscar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,
                                                     @Parameter(description = "ID do Cliente a ser buscado") @PathVariable long id) {
        return ResponseEntity.ok(this.ClienteService.getCliente(owner_id, id));
    }

    @PostMapping("/{owner_id}")
    @Operation(summary = "Criar novo Cliente", description = "Criar um novo Cliente e adicionar à lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosClienteOutput.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<DadosClienteOutput> salvar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,
                                                     @RequestBody @Valid Clientes cliente, UriComponentsBuilder uriBuilder) {
        DadosClienteOutput dc = this.ClienteService.salvar(owner_id, cliente);
        URI uri = uriBuilder.path("/clente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(dc);
    }

    @PutMapping("{owner_id}")
    @Operation(summary = "Atualizar Cliente", description = "Atualiza um Cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)

    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<DadosClienteInput> atualizar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @RequestBody @Valid DadosClienteInput cliente) {
        DadosClienteOutput dc = this.ClienteService.editar(cliente);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Cliente por ID", description = "Remove o Cliente correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso",content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity deletar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,
                                  @Parameter(description = "ID do Cliente a ser deletado") @PathVariable long id) {
        this.ClienteService.excluir(owner_id, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{owner_id}/{id}/addPet")
    @Transactional
    @Operation(summary = "Adicionar Pet ao Cliente", description = "Atribui um novo Pet ao Cliente")
    @ApiResponses(value = {
            // Documentação adicionada
            @ApiResponse(responseCode = "204", description = "Pet atribuído com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity addPet(@Parameter(description = "ID do Proprietário que receberá o Usuário") @PathVariable long owner_id, @Parameter(description = "ID do Proprietário que receberá o Usuário") @PathVariable long id, @RequestBody @Valid DadosPetInput pet) {
        this.ClienteService.atribuirPet(owner_id, id, pet);
        return ResponseEntity.noContent().build();
    }


}
