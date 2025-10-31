package br.cspi.model.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;

public record DadosPetInput(
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(description = "Identificador único do Pet.", example = "101", hidden = true)
        Long id,

        @NotBlank
        @Schema(description = "Nome do Pet.", example = "Thor")
        String nomepet,

        @NotBlank
        @Schema(description = "Espécie do Pet (ex: Canino, Felino).", example = "Canino")
        String especie,

        @NotBlank
        @Schema(description = "Raça do Pet.", example = "Golden Retriever")
        String raca,

        @NotBlank
        @Pattern(regexp = "^(M|F)$",
                message = "O sexo deve ser 'M' (Macho) ou 'F' (Femea).")
        @Schema(description = "Sexo do Pet (M/F).", example = "M")
        String sexo,

        @NotBlank
        @Schema(description = "Descrição detalhada ou observações sobre o Pet.", example = "Porte grande, castrado.")
        String descricao,

        @Schema(description = "Data de criação")
        OffsetDateTime data_cricao,

        @Schema(description = "ID do Tutor (Cliente) que é o dono direto do Pet.", hidden = true, example = "5")
        Long tutor_id,

        @Schema(description = "ID do Proprietário (campo de sistema/relacionamento).", hidden = true, example = "1")
        Long owner_id){

    public DadosPetInput(Pet p){
        this(p.getId(),p.getNomepet(), p.getEspecie(),p.getRaca(),p.getSexo(),p.getDescricao(),(OffsetDateTime) p.getData_cricao(), p.getTutor().getId(), p.getOwner().getId());
    }


}
