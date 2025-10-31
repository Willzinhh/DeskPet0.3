package br.cspi.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public record DadosOwnerInput(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "Identificador único (ID) do Proprietário.", example = "1", hidden = true)
        Long id,

        @UuidGenerator
        @Schema(description = "Identificador universalmente único (UUID) da conta.", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef", hidden = true)
        UUID uuid,

        @NotBlank
        @Schema(description = "Nome completo do Proprietário.", example = "Carlos Souza")
        String nome,

        @NotBlank
        @Size(min = 14, max = 14, message = "CPF deve seguir o formato XXX.XXX.XXX-XX ")
        @Schema(description = "CPF (Cadastro de Pessoa Física) do Proprietário.", example = "111.222.333-44")
        String cpf,

        @Size(min = 18, max = 18, message = "CNPJ deve seguir o formato XX.XXX.XXX/0001-AA")
        @Schema(description = "CNPJ (Cadastro Nacional da Pessoa Jurídica) da empresa (se aplicável).", example = "00.000.000/0001-00")
        String cnpj,

        @NotBlank
        @Size(min = 14, max = 14, message = "Telefone deve seguir o formato (XX)XXXXX-XXXX")
        @Schema(description = "Número de telefone para contato.", example = "(21)98765-4321")
        String telefone,

        @NotBlank
        @Schema(description = "Endereço principal.", example = "Rua das Flores, 100")
        String endereco,

        @NotBlank
        @Schema(description = "Nome da empresa/negócio.", example = "PetShop Fantástico Ltda")
        String nome_empresa,

        @NotBlank
        @Pattern(regexp = "^(Free|Basic|Premium)$",
                message = "O plano deve ser 'Free', 'Basic' ou 'Premium'.")
        @Schema(description = "Plano de assinatura do Proprietário", allowableValues = {"Free", "Basic", "Premium"}, example = "Premium")
        String plano
) {
}
