//package br.cspi.model.pet;
//
//public record DadosPetOutput (Long id,
//                                     String nomepet,
//                                     String especie,
//                                     String raca,
//                                     String sexo,
//                                     String descricao,
//                                     Long tutor_id,
//                                     Long owner_id)
//{
//
//    public DadosPetOutput(Pet p){
//        this(p.getId(),p.getNomepet(), p.getEspecie(),p.getRaca(),p.getSexo(),p.getDescricao(), p.getTutor().getId(), p.getOwner().getId());
//    }
//}
//

package br.cspi.model.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import br.cspi.model.pet.Pet; // Assumindo que a entidade Pet está neste package ou acessível

import java.sql.Timestamp;
import java.time.OffsetDateTime;


/**
 * DTO de saída que representa as informações de um Pet.
 */
public record DadosPetOutput (

        @Schema(description = "Identificador único do Pet.", example = "101")
        Long id,

        @Schema(description = "Nome do Pet.", example = "Thor")
        String nomepet,

        @Schema(description = "Espécie do Pet (ex: Canino, Felino).", example = "Canino")
        String especie,

        @Schema(description = "Raça do Pet.", example = "Golden Retriever")
        String raca,

        @Schema(description = "Sexo do Pet (M/F).", example = "M")
        String sexo,

        @Schema(description = "Descrição detalhada ou observações sobre o Pet.", example = "Porte grande, castrado.")
        String descricao,

        @Schema(description = "Data de criação")
        OffsetDateTime data_cricao

        )
{

    public DadosPetOutput(Pet p){
        this(p.getId(),
                p.getNomepet(),
                p.getEspecie(),
                p.getRaca(),
                p.getSexo(),
                p.getDescricao(),
                (OffsetDateTime) p.getData_cricao()
                );
    }
}