package br.cspi.model.agendamento;

import org.w3c.dom.Text;

import java.sql.Time;
import java.time.OffsetDateTime;
import java.util.Date;

public record DadosAgendamento(Long id, OffsetDateTime data, OffsetDateTime horario, String observacao, String status, String  pagamento, Long pet_id, Long servico_id, Long funcionario_id, Long owner_id) {
    public DadosAgendamento(Agendamento a) {
        this((long) a.getId(), a.getData(), a.getHorario(), a.getObservacao(), a.getStatus(), a.getPagamento(), a.getPet().getId(),  a.getServico().getId(),  a.getFuncionario().getId(), a.getOwner().getId());
    }


}
