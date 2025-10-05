package br.cspi.model.servico;

import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// ... (outros imports)

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes de um Serviço oferecido pela clínica/pet shop.") // <--- Adicionado
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Serviço", example = "3") // <--- Adicionado
    private int id;
    @Schema(description = "Nome do Serviço", example = "Banho e Tosa") // <--- Adicionado
    private String nome;
    @Schema(description = "Descrição detalhada do Serviço", example = "Inclui corte de pelo, limpeza de ouvidos e corte de unhas.") // <--- Adicionado
    private String descricao;
    @Schema(description = "Valor cobrado pelo Serviço", example = "85.00") // <--- Adicionado
    private double valor;
    @Schema(description = "Tempo médio de duração do Serviço (em formato 'HH:MM')", example = "01:30") // <--- Adicionado
    private String tempo;
    @Schema(description = "ID do Proprietário (Owner) ou Usuário (User) que gerencia o registro", example = "1") // <--- Adicionado
    private int cliente_usuario_id;


}