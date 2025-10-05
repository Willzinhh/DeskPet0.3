package br.cspi.model.agendamento;

import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
// ... (outros imports)

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalhes de um Agendamento (Serviço) na agenda da clínica/pet shop.") // <--- Adicionado
public class Agendamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Agendamento", example = "50") // <--- Adicionado
    private int id;
    @Schema(description = "Data do Agendamento", example = "2025-10-25") // <--- Adicionado
    private Date data;
    @Schema(description = "Horário do Agendamento", example = "14:30:00") // <--- Adicionado
    private Time horario;
    @Schema(description = "ID do Pet agendado", example = "101") // <--- Adicionado
    private int pet_id;
    @Schema(description = "ID do Serviço agendado (e.g., Banho, Tosa)", example = "3") // <--- Adicionado
    private int servico_id;
    @Schema(description = "ID do Funcionário responsável pelo serviço", example = "5") // <--- Adicionado
    private int funcionario_id;
    @Schema(description = "Observações adicionais sobre o agendamento", example = "Pet arisco, necessário focinheira.") // <--- Adicionado
    private String observacao;
    @Schema(description = "Status atual do agendamento", allowableValues = {"PENDENTE", "CONCLUIDO", "CANCELADO"}, example = "PENDENTE") // <--- Adicionado
    private String status;
    @Schema(description = "Forma de pagamento utilizada", allowableValues = {"DEBITO", "CREDITO", "PIX", "DINHEIRO"}, example = "PIX") // <--- Adicionado
    private String pagamento;
    @Schema(description = "ID do Proprietário (Owner) ou Usuário (User) que criou/gerencia o agendamento", example = "1") // <--- Adicionado
    private int cliente_usuario_id;

    // campos extras (opcional)
    @Schema(description = "Nome do Pet (campo extra para exibição)") // <--- Adicionado
    private String petNome;
    @Schema(description = "Nome do Serviço (campo extra para exibição)") // <--- Adicionado
    private String servicoNome;
    @Schema(description = "Nome do Funcionário (campo extra para exibição)") // <--- Adicionado
    private String funcionarioNome;




}