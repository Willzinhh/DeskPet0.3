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
        Timestamp data_criacao,

        @Schema(description = "ID do Tutor (Cliente) que é o dono direto do Pet.", hidden = true, example = "5")
        Long tutor_id,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        Long owner_id)
{

    public DadosPetOutput(Pet p){
        this(p.getId(),
                p.getNomepet(),
                p.getEspecie(),
                p.getRaca(),
                p.getSexo(),
                p.getDescricao(),
                (Timestamp) p.getData_cricao(),
                p.getTutor().getId(), // Assumindo que p.getTutor() e p.getOwner() retornam objetos válidos
                p.getOwner().getId());
    }
}