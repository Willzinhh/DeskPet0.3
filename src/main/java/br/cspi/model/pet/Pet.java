package br.cspi.model.pet;

import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes do Pet") // Anotação de classe
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Pet", example = "101")
    private Long id;
    @Schema(description = "Nome do Pet", example = "Rex")
    private String nomepet;
    @Schema(description = "Espécie do Pet", example = "Cachorro")
    private String especie;
    @Schema(description = "Raça do Pet", example = "Golden Retriever")
    private String raca;
    @Schema(description = "Sexo do Pet (M/F)", example = "M")
    private String sexo;
    @Schema(description = "Descrição ou observações sobre o Pet", example = "Castrado, alergia a frango.")
    private String descricao;
    @Schema(description = "Data de criação do registro do Pet")
    private Date data_cricao;
    @Schema(description = "ID do Tutor (Cliente) responsável pelo Pet", example = "5")
    private int tutor_id;
    @Schema(description = "ID do Proprietário (Owner) que gerencia o registro", example = "1")
    private int owner_id;

}