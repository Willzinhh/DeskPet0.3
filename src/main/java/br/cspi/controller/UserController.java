package br.cspi.controller;


import br.cspi.dto.UserDTO;
import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.User;
import br.cspi.service.UserService;
import io.swagger.v3.oas.annotations.Operation; // Import adicionado
import io.swagger.v3.oas.annotations.Parameter; // Import adicionado
import io.swagger.v3.oas.annotations.media.Content; // Import adicionado
import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Import adicionado
import io.swagger.v3.oas.annotations.responses.ApiResponses; // Import adicionado
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de Usuarios (Users).")
public class UserController {

    private UserService UserService;

    HttpSession session;


    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Usuários", description = "Lista todos os Usuários cadastrados de um Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosUser.class))),
            @ApiResponse(responseCode = "404", description = "Owner não encontrado", content = @Content)
    })
    public ResponseEntity<List<DadosUser>> listar(@Parameter(description = "ID do Proprietario") @PathVariable() long owner_id) {
        return ResponseEntity.ok(this.UserService.listar(owner_id));
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Retorna Usuário correspondete ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)

    })
    public DadosUser buscar(@Parameter(description = "ID do Proprietário/Tenant que detém o usuário.") @PathVariable long owner_id,
                            @Parameter(description = "ID do Usuário específico a ser buscado.") @PathVariable long id) throws Throwable {
        return ResponseEntity.ok(this.UserService.getUser(owner_id, id)).getBody();
    }

    @PutMapping("/{owner_id}")
    @Transactional
    @Operation(summary = "Atualizar Usuário", description = "Atualiza um Usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)

    })
    public ResponseEntity<DadosUser> atualizar(@Parameter(description = "ID do Usuário a ser deletado") @PathVariable long owner_id ,@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        DadosUser du = this.UserService.editar(user, owner_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{owner_id}/{id}") // Adicionei o path variable aqui
    @Operation(summary = "Deletar Usuário por ID", description = "Remove o Usuário correspondente ao ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Usuário a ser deletado") @PathVariable long owner_id,@Parameter(description = "ID do Usuário a ser deletado") @PathVariable long id) { // Adicionei o path variable aqui
        this.UserService.excluir(owner_id,id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Criar novo Usuario", description = "Cria um novo Usuario")
    @ApiResponses(value = {
            // Corrigido para 201 Created (POST) e 400 (Bad Request)
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content)
    })
    public ResponseEntity<DadosUser> salvar(@RequestBody @Valid UriComponentsBuilder uriBuilder, User user) {
        DadosUser uc = UserService.salvar(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(uc);
    }

}