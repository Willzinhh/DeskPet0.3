package br.cspi.controller;


import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.User;
import br.cspi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "Usuario", description = "Endpoints para gerenciamento de Usuarios (Users) e Tenant (multi-owner).")
public class UserController {

    private UserService UserService;

    HttpSession session;


    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Usuários de um Owner", description = "Lista todos os Usuários cadastrados pertencentes a um Owner específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários Encontrados", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosUser.class))),
            @ApiResponse(responseCode = "404", description = "Owner não encontrado", content = @Content),
            // Adicionado 403 Forbidden para cobrir a restrição de segurança
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<List<DadosUser>> listar(@Parameter(description = "ID do Proprietario/Tenant para listar os Usuários.") @PathVariable() Long owner_id) {
        return ResponseEntity.ok(this.UserService.listar(owner_id));
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Retorna o Usuário correspondete ao ID fornecido, dentro do contexto do Owner.")
    @ApiResponses(value = {
            // Schema alterado para DadosUser para consistência com o retorno do método
            @ApiResponse(responseCode = "200", description = "Usuário Encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosUser.class))),
            @ApiResponse(responseCode = "404", description = "Usuário ou Proprietário não encontrado", content = @Content),
            // Adicionado 403 Forbidden para cobrir a restrição de segurança
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content)

    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public DadosUser buscar(@Parameter(description = "ID do Proprietário/Tenant que detém o usuário.") @PathVariable long owner_id,
                            @Parameter(description = "ID do Usuário específico a ser buscado.") @PathVariable long id) throws Throwable {
        return ResponseEntity.ok(this.UserService.getUser(owner_id, id)).getBody();
    }

    @PutMapping("/{owner_id}")
    @Transactional
    @Operation(summary = "Atualizar Usuário", description = "Atualiza um Usuário existente.")
    @ApiResponses(value = {
            // Removido content=@Content do 204 pois não deve haver corpo na resposta
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)

    })
    public ResponseEntity<DadosUser> atualizar(
            @Parameter(description = "ID do Proprietário/Tenant ao qual o Usuário pertence.") @PathVariable long owner_id, // Descrição corrigida
            @RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        DadosUser du = this.UserService.editar(user, owner_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Usuário por ID", description = "Remove o Usuário correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            // Adicionado 403 Forbidden para cobrir a restrição de segurança
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui as roles 'ADMIN' ou 'OWNER'.", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity deletar(
            @Parameter(description = "ID do Proprietário/Tenant ao qual o Usuário pertence.") @PathVariable long owner_id, // Descrição corrigida
            @Parameter(description = "ID do Usuário específico a ser deletado.") @PathVariable long id) { // Descrição corrigida
        this.UserService.excluir(owner_id, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Criar novo Usuario (Owner ou Usuário padrão)", description = "Cria um novo Usuário (com role 'OWNER' se tentar usar 'ADMIN'). **Requer permissão de ADMIN ou OWNER.**")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosUser.class))), // Schema alterado para DadosUser
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            // Adicionado 403 Forbidden para cobrir a restrição de segurança
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário autenticado não é 'ADMIN' nem 'OWNER'.", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    // CORREÇÃO CRÍTICA: Corrigi a anotação @RequestBody para o objeto User e a ordem dos parâmetros.
    public ResponseEntity<DadosUser> salvar(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        if (user.getPermissao().equals("ADMIN")) {
            user.setPermissao("OWNER");
        }
        DadosUser uc = UserService.salvar(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(uc);
    }

    @PostMapping("/admin")
    @Operation(summary = "Criar novo Usuario Admin", description = "Cria um novo Usuário com permissão de ADMIN. **Requer permissão de ADMIN do usuário solicitante.**")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosUser.class))), // Schema alterado para DadosUser
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso Negado: O usuário não possui a role 'ADMIN'.", content = @Content)

    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DadosUser> salvarAdmin(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
        user.setPermissao("ADMIN");
        DadosUser uc = UserService.salvar(user);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(uc);
    }

}
