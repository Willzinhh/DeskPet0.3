package br.cspi.model.servico;

import io.swagger.v3.oas.annotations.media.Schema;

public record InputDadosServico(
        @Schema(description = "Identificador único do Serviço.", example = "201")
        Long id,

        @Schema(description = "Nome do Serviço.", example = "Banho e Tosa")
        String nome,

        @Schema(description = "Descrição detalhada do Serviço.", example = "Serviço de higienização completa com corte de pelo.")
        String descricao,

        @Schema(description = "Valor (preço) do Serviço.", example = "55.00")
        double valor,

        @Schema(description = "Tempo estimado de duração do Serviço.", example = "1 hora")
        String tempo,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        Long owner_id) {
}
