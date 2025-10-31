package br.cspi.model.pet;

import br.cspi.model.cliente.Clientes; // Import necessário
import br.cspi.model.usuario.Owner;    // Import necessário
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes do Pet")
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
    @NotBlank
    @Pattern(regexp = "^(M|F)$",
            message = "O sexo deve ser 'M' (Macho) ou 'F' (Femea).")
    @Schema(description = "Sexo do Pet (M/F)", example = "M")
    private String sexo;
    @Schema(description = "Descrição ou observações sobre o Pet", example = "Castrado, alergia a frango.")
    private String descricao;
    @Schema(description = "Data de criação do registro do Pet")
    private OffsetDateTime data_cricao;

    // CORREÇÃO: Mapeamento N:1 para o Tutor (Clientes)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Objeto Tutor (Cliente) responsável pelo Pet", hidden = true)
    private Clientes tutor;



    // CORREÇÃO: Mapeamento N:1 para o Owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @Schema(description = "Proprietário (Owner) que gerencia o registro", hidden = true)
    private Owner owner;

    // Os campos 'private int tutor_id;' e 'private int owner_id;' foram removidos.
}