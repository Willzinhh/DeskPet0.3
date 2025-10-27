package br.cspi.model.agendamento;

import java.time.Instant;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de Saída para Agendamento. Usado para retornar dados de forma simplificada,
 * evitando aninhamento excessivo e carregando apenas IDs de entidades relacionadas.
 */
public record DadosAgendamentoOutput(
        @Schema(description = "ID único do Agendamento", example = "50")
        Long id,

        @Schema(description = "Data do agendamento (formato ISO 8601)", example = "2025-10-27T02:16:16.513Z")
        OffsetDateTime data,

        @Schema(description = "Horário do agendamento (formato ISO 8601)", example = "2025-10-27T02:16:16.513Z")
        OffsetDateTime horario,

        // REFERÊNCIAS APENAS POR ID
        @Schema(description = "ID do Pet agendado", example = "101")
        Long petId,

        @Schema(description = "ID do Serviço contratado", example = "3")
        Long servicoId,

        @Schema(description = "ID do Funcionário responsável", example = "5")
        Long funcionarioId,

        @Schema(description = "Observações sobre o agendamento", example = "Pet arisco, necessário focinheira.")
        String observacao,

        @Schema(description = "Status atual do agendamento", allowableValues = {"PENDENTE", "CONCLUIDO", "CANCELADO"}, example = "PENDENTE")
        String status,

        @Schema(description = "Método de pagamento", allowableValues = {"PIX", "CARTAO", "DINHEIRO"}, example = "PIX")
        String pagamento
) {
    /**
     * Construtor para mapear a entidade Agendamento para este DTO de Saída.
     * @param agendamento A entidade JPA Agendamento.
     */
    public DadosAgendamentoOutput(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getHorario(),
                agendamento.getPet().getId(), // Pega apenas o ID
                agendamento.getServico().getId(), // Pega apenas o ID
                agendamento.getFuncionario().getId(), // Pega apenas o ID
                agendamento.getObservacao(),
                agendamento.getStatus(),
                agendamento.getPagamento()
        );
    }
}
