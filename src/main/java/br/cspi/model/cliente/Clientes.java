package br.cspi.model.cliente;

import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes do Cliente (Tutor)") // Anotação de classe
public class Clientes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Cliente", example = "5")
    private Long id;
    @NotBlank
    @Schema(description = "Nome completo do Cliente", example = "Maria Oliveira")
    private String nome;
    @NotBlank
    @Schema(description = "Telefone para contato", example = "(21)99887-7665")
    private String telefone;
    @Schema(description = "CPF do Cliente (Opcional)", example = "987.654.321-11", nullable = true)
    private String cpf;
    @Schema(description = "Endereço completo do Cliente", example = "Av. Principal, 456 - Bairro X")
    private String endereco;
    @Schema(description = "Data e hora de criação do registro do Cliente")
    private Timestamp data_criacao;
    @Schema(description = "ID do Proprietário (Owner) que gerencia este Cliente", example = "1")
    private int owner_id;

}