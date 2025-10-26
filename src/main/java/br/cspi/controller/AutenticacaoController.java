package br.cspi.controller;

import br.cspi.infra.security.TokenServiceJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
// Adicionar o Tag ajuda o Springdoc a reconhecer o Controller
@Tag(name = "Autenticação", description = "Endpoints para Login e geração de Token JWT")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenServiceJWT;

    public AutenticacaoController(AuthenticationManager manager, TokenServiceJWT tokenServiceJWT) {
        this.manager = manager;
        this.tokenServiceJWT = tokenServiceJWT;
    }

    @PostMapping
    @Operation(summary = "Realiza o login e retorna um token JWT", description = "Endpoint público. Não requer autorização.")
// O endpoint de login é o único que não deve exigir o SecurityRequirement,
// mas anotações como @Tag e @Operation são importantes.
    public ResponseEntity<?> efetuarLogin(@RequestBody DadosAutenticacao dados){
        try{
            Authentication autenticado = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication at = manager.authenticate(autenticado);

            User user = (User) at.getPrincipal();
            String token = this.tokenServiceJWT.gerarToken(user);

            return ResponseEntity.ok().body(new DadosTokenJWT (token));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private record DadosAutenticacao(String email, String senha){

    }

    private record DadosTokenJWT(String token){}
}

