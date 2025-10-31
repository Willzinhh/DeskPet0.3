package br.cspi.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record DadosOwnerInput(
        @Schema(description = "Identificador único (ID) do Proprietário.", example = "1", hidden = true)
        Long id,

        @Schema(description = "Identificador universalmente único (UUID) da conta.", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef", hidden = true)
        UUID uuid,

        @Schema(description = "Nome completo do Proprietário.", example = "Carlos Souza")
        String nome,

        @Schema(description = "CPF (Cadastro de Pessoa Física) do Proprietário.", example = "111.222.333-44")
        String cpf,

        @Schema(description = "CNPJ (Cadastro Nacional da Pessoa Jurídica) da empresa (se aplicável).", example = "00.000.000/0001-00")
        String cnpj,

        @Schema(description = "Número de telefone para contato.", example = "(21)98765-4321")
        String telefone,

        @Schema(description = "Endereço principal.", example = "Rua das Flores, 100")
        String endereco,

        @Schema(description = "Nome da empresa/negócio.", example = "PetShop Fantástico Ltda")
        String nome_empresa,

        @Pattern(regexp = "^(Free|Basic|Premium)$",
                message = "O plano deve ser 'Free', 'Basic' ou 'Premium'.")
        @Schema(description = "Plano de assinatura do Proprietário", allowableValues = {"Free", "Basic", "Premium"}, example = "Premium")
        String plano
) {
}
