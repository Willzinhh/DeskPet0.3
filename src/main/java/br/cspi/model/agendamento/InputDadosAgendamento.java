package br.cspi.model.agendamento;

import org.w3c.dom.Text;

import java.time.OffsetDateTime;

public record InputDadosAgendamento (long id, OffsetDateTime data, OffsetDateTime horario, String observacao, String status, String pagamento, long pet_id, long servico_id, long funcionario_id, long owner_id){
}
