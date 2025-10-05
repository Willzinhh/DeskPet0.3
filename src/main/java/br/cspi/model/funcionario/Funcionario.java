package br.cspi.model.funcionario;

import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
// ... (outros imports)

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes de um Funcionário (Usuário) da clínica/pet shop.") // <--- Adicionado

public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Funcionário", example = "5") // <--- Adicionado
    private int id;

    @Schema(description = "Nome completo do Funcionário", example = "Ana Costa") // <--- Adicionado
    private String nome;
    @Schema(description = "CPF do Funcionário", example = "321.654.987-00") // <--- Adicionado
    private String cpf;
    @Schema(description = "Telefone para contato", example = "(11)99999-8888") // <--- Adicionado
    private String telefone;
    @Schema(description = "Cargo ou função do Funcionário", example = "Veterinário") // <--- Adicionado
    private String cargo;
    @Schema(description = "Salário ou base salarial do Funcionário", example = "3500.00") // <--- Adicionado
    private BigDecimal salario;
    @Schema(description = "Status de atividade do Funcionário", example = "true") // <--- Adicionado
    private boolean ativo;
    @Schema(description = "ID do Proprietário (Owner) ou Usuário (User) que gerencia o registro", example = "1") // <--- Adicionado
    private int cliente_usuario_id; // NOVO CAMPO


}