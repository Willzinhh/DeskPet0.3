package br.cspi.model.servico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InputDadosServico(
        @Schema(description = "Identificador único do Serviço.", example = "201")
        Long id,

        @NotBlank
        @Schema(description = "Nome do Serviço.", example = "Banho e Tosa")
        String nome,

        @NotBlank
        @Schema(description = "Descrição detalhada do Serviço.", example = "Serviço de higienização completa com corte de pelo.")
        String descricao,

        @NotNull
        @Positive
        @Schema(description = "Valor (preço) do Serviço.", example = "55.00")
        double valor,

        @NotBlank
        @Schema(description = "Tempo estimado de duração do Serviço.", example = "1 hora")
        String tempo,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        Long owner_id) {
}
