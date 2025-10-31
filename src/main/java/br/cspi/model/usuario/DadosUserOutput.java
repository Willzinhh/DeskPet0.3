package br.cspi.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public record DadosUserOutput(

        @Schema(description = "Identificador único do Usuário.", example = "1")
        Long id,

        @Schema(description = "Nome completo do Usuário.", example = "Alice Rocha")
        String nome,

        @Schema(description = "Endereço de e-mail do Usuário.", example = "alice.rocha@empresa.com")
        String email,

        @Schema(description = "Nível de permissão/acesso do Usuário (ex: ADMIN, FUNCIONARIO, CLIENTE).", example = "ADMIN")
        String permissao) {

    public DadosUserOutput(User user) {
        this(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getPermissao());
    }
}
