package br.cspi.controller;


import br.cspi.model.usuario.User;
import br.cspi.service.UserService;
import io.swagger.v3.oas.annotations.Operation; // Import adicionado
import io.swagger.v3.oas.annotations.Parameter; // Import adicionado
import io.swagger.v3.oas.annotations.media.Content; // Import adicionado
import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Import adicionado
import io.swagger.v3.oas.annotations.responses.ApiResponses; // Import adicionado
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de Usuarios (Users).")

public class UserController {

    private UserService UserService;
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Usuários", description = "Lista todos os Usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
    })
    public List<User> listar() {
        return UserService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Retorna Usuário correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)

    })
    public User buscar(@Parameter(description = "ID do Usuário a ser buscado") @PathVariable long id) {
        return this.UserService.getUser(id);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar Usuário", description = "Atualiza um Usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)

    })
    public ResponseEntity atualizar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        this.UserService.salvar(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/{id}") // Adicionei o path variable aqui
    @Operation(summary = "Deletar Usuário por ID", description = "Remove o Usuário correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Usuário a ser deletado") @PathVariable long id) { // Adicionei o path variable aqui
        this.UserService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}