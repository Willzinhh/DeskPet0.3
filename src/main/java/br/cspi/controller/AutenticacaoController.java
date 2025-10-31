package br.cspi.controller;

import br.cspi.infra.security.TokenServiceJWT;
import br.cspi.model.usuario.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;


@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Endpoints para Login e geração de Token JWT")
public class AutenticacaoController {


    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenServiceJWT;
    private final UserRepository userRepository;

    public AutenticacaoController(AuthenticationManager manager, TokenServiceJWT tokenServiceJWT, UserRepository userRepository) {
        this.manager = manager;
        this.tokenServiceJWT = tokenServiceJWT;
        this.userRepository = userRepository;
    }

    @PostMapping
    @Operation(summary = "Realiza o login e retorna um token JWT", description = "Endpoint público. Não requer autorização.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido. Retorna o token JWT.",content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema( example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos na requisição (e-mail ou senha ausentes/inválidos).", content = @Content),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas (e-mail ou senha incorretos).", content = @Content),
    })
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication at = manager.authenticate(autenticado);

            br.cspi.model.usuario.User userr = userRepository.findByEmail(dados.email());

            User user = (User) at.getPrincipal();
            String token = this.tokenServiceJWT.gerarToken(userr);

            return ResponseEntity.ok().body(new DadosTokenJWT(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: Credenciais inválidas.");
        }
    }

    private record DadosAutenticacao(String email, String senha) {

    }

    private record DadosTokenJWT(String token) {
    }
}

