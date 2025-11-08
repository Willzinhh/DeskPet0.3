package br.cspi.controller;


import br.cspi.model.usuario.*;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/owner")
@ControllerAdvice
@Tag(name = "Owner", description = "Endpoints para gerenciamento de Proprietários (Owners).")
public class OwnerController {

    private final UserRepository userRepository;
    private OwnerService OwnerService;

    @GetMapping("/listar")
    @Operation(summary = "Listar Proprietarios", description = "Lista todos os Proprietarios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietarios Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosOwnerOutput.class))),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui a role 'ADMIN'.", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public List<DadosOwnerOutput> listar() {
        return OwnerService.listar();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Proprietario por ID", description = "Retorna Proprietario correspondente ao ID fornecido")
    @ApiResponses(value = {
            // Corrigido para 200 OK (GET Request) e 404 (Não encontrado)
            @ApiResponse(responseCode = "200", description = "Proprietario Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosOwnerOutput.class))),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui a role 'ADMIN'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<DadosOwnerOutput> buscar(@Parameter(description = "ID do Proprietario a ser buscado") @PathVariable long id) {
        return this.OwnerService.getOwner(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo Proprietario", description = "Cria um novo Proprietário")
    @ApiResponses(value = {
            // Corrigido para 201 Created (POST) e 400 (Bad Request)
            @ApiResponse(responseCode = "201", description = "Proprietario criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosOwnerOutput.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content)

    })

    public ResponseEntity salvar(@RequestBody @Valid DadosOwnerInput owneri, UriComponentsBuilder uriBuilder) {
        Owner owner = this.OwnerService.salvar(owneri);
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
    public ResponseEntity<Owner> atualizar(@RequestBody @Valid Owner owner) {

        this.OwnerService.atualizar(owner);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Proprietario por ID", description = "Remove o Proprietário correspondente ao ID fornecido")
    @ApiResponses(value = {
            // Documentação adicionada
            @ApiResponse(responseCode = "204", description = "Proprietario excluído com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui a role 'ADMIN'.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietario não encontrado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletar(@Parameter(description = "ID do Proprietario a ser deletado") @PathVariable long id) {
        this.OwnerService.excluir(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{owner_id}/addUser")
    @Transactional
    @Operation(summary = "Adicionar Usuário ao Proprietário", description = "Atribui um novo Usuário ao Proprietário especificado pelo ID e retorna o Owner atualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atribuído com sucesso. Retorna o Proprietário atualizado.",content = @Content(mediaType ="application/json",
            schema = @Schema(implementation = User.class))),

            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content)
    })
    public ResponseEntity<User> addUser(@Parameter(description = "ID do Proprietário que receberá o Usuário") @PathVariable long owner_id, @RequestBody @Valid User user ,UriComponentsBuilder uriBuilder) {
        User usern = this.OwnerService.atribuirUser(owner_id, user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(usern.getId()).toUri();
        return ResponseEntity.created(uri).body(usern);
    }


}