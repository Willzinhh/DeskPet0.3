package br.cspi.controller;


import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.User;
import br.cspi.service.OwnerService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/owner")
@Tag(name = "Owner", description = "Endpoints para gerenciamento de Proprietários (Owners).")

public class OwnerController {

    private OwnerService OwnerService;

    @GetMapping("/listar")
    @Operation(summary = "Listar Proprietarios", description = "Lista todos os Proprietarios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietarios Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Owner.class))),
    })
    public List<Owner> listar() {
        return OwnerService.listar();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Proprietario por ID", description = "Retorna Proprietario correspondente ao ID fornecido")
    @ApiResponses(value = {
            // Corrigido para 200 OK (GET Request) e 404 (Não encontrado)
            @ApiResponse(responseCode = "200", description = "Proprietario Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Owner.class))),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado", content = @Content)
    })
    public Owner buscar(@Parameter(description = "ID do Proprietario a ser buscado") @PathVariable long id) {
        return this.OwnerService.getOwner(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo Proprietario", description = "Cria um novo Proprietário")
    @ApiResponses(value = {
            // Corrigido para 201 Created (POST) e 400 (Bad Request)
            @ApiResponse(responseCode = "201", description = "Proprietario criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Owner.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content)

    })
    public ResponseEntity salvar(@RequestBody @Valid Owner owner, UriComponentsBuilder uriBuilder) {
        this.OwnerService.salvar(owner);
        URI uri = uriBuilder.path("/owner/{id}").buildAndExpand(owner.getId()).toUri();
        return ResponseEntity.created(uri).body(owner);
    }

    @PutMapping
    @Operation(summary = "Atualizar Proprietario", description = "Atualiza um Proprietário existente")
    @ApiResponses(value = {
            // Documentação adicionada
            @ApiResponse(responseCode = "204", description = "Proprietario atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado", content = @Content)
    })
    public ResponseEntity<Owner> atualizar(@RequestBody @Valid Owner owner ) {

        this.OwnerService.atualizar(owner);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Proprietario por ID", description = "Remove o Proprietário correspondente ao ID fornecido")
    @ApiResponses(value = {
            // Documentação adicionada
            @ApiResponse(responseCode = "204", description = "Proprietario excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Proprietario a ser deletado") @PathVariable long id) {
        this.OwnerService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/addUser")
    @Transactional
    @Operation(summary = "Adicionar Usuário ao Proprietário", description = "Atribui um novo Usuário ao Proprietário especificado pelo ID")
    @ApiResponses(value = {
            // Documentação adicionada
            @ApiResponse(responseCode = "204", description = "Usuário atribuído com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Owner.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content)
    })
    public ResponseEntity addUser(@Parameter(description = "ID do Proprietário que receberá o Usuário") @PathVariable long id, @RequestBody @Valid User user) {
        return ResponseEntity.ok(this.OwnerService.atribuirUser(id,user));
    }


}