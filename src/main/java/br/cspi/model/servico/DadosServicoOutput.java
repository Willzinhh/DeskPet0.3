//package br.cspi.model.servico;
//
//public record DadosServicoOutput(Long id, String nome, String descricao , double valor , String tempo, Long owner_id) {
//    public DadosServicoOutput(Servico s) {
//        this((long) s.getId(), s.getNome(), s.getDescricao(), s.getValor(), s.getTempo(),s.getOwner().getId());
//    }
//}
package br.cspi.model.servico;

import io.swagger.v3.oas.annotations.media.Schema;
import br.cspi.model.servico.Servico; // Assumindo que a entidade Servico está acessível

/**
 * DTO de saída que representa as informações de um Serviço oferecido.
 */
public record DadosServicoOutput(

        @Schema(description = "Identificador único do Serviço.", example = "201")
        Long id,

        @Schema(description = "Nome do Serviço.", example = "Banho e Tosa")
        String nome,

        @Schema(description = "Descrição detalhada do Serviço.", example = "Serviço de higienização completa com corte de pelo.")
        String descricao,

        @Schema(description = "Valor (preço) do Serviço.", example = "55.00")
        double valor,

        @Schema(description = "Tempo estimado de duração do Serviço.", example = "1 hora")
        String tempo) {

    public DadosServicoOutput(Servico s) {
        this((long) s.getId(),
                s.getNome(),
                s.getDescricao(),
                s.getValor(),
                s.getTempo()
                ); // Assumindo que s.getOwner() retorna um objeto que tem o método getId()
    }
}
