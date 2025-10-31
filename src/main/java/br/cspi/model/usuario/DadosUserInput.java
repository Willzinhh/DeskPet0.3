package br.cspi.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

public record DadosUserInput(@Schema(description = "Identificador único do Usuário.", example = "1")
                             Long id,

                             @Schema(description = "Nome completo do Usuário.", example = "Alice Rocha")
                             String nome,

                             @Schema(description = "Endereço de e-mail do Usuário.", example = "alice.rocha@empresa.com")
                             String email,

                             @Schema(description = "Senha do Usuário", example = "98562846")
                             String senha,

                             @Schema(description = "Nível de permissão/acesso do Usuário (ex: ADMIN, FUNCIONARIO, CLIENTE).", example = "ADMIN")
                             String permissao
) {

}
