package br.cspi.model.agendamento;

import br.cspi.model.pet.Pet;             // Import necessário
import br.cspi.model.servico.Servico;     // Import necessário
import br.cspi.model.funcionario.Funcionario; // Import necessário
import br.cspi.model.usuario.Owner;      // Import necessário
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
// ... (outros imports)

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalhes de um Agendamento (Serviço) na agenda da clínica/pet shop.")
public class Agendamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Agendamento", example = "50")
    private Long id;
    @Schema(description = "Data do Agendamento", example = "2025-10-25")
    private OffsetDateTime data;
    @Schema(description = "Horário do Agendamento", example = "14:30:00")
    private OffsetDateTime horario;

    // CORREÇÃO: Mapeamento N:1 para o Pet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Schema(description = "Objeto Pet agendado")
    private Pet pet;

    // CORREÇÃO: Mapeamento N:1 para o Servico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    @Schema(description = "Objeto Serviço agendado")
    private Servico servico;

    // CORREÇÃO: Mapeamento N:1 para o Funcionario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    @Schema(description = "Objeto Funcionário responsável pelo serviço")
    private Funcionario funcionario;

    @Schema(description = "Observações adicionais sobre o agendamento", example = "Pet arisco, necessário focinheira.")
    private String observacao;
    @Schema(description = "Status atual do agendamento", allowableValues = {"PENDENTE", "CONCLUIDO", "CANCELADO"}, example = "PENDENTE")
    private String status;
    @Schema(description = "Forma de pagamento utilizada", allowableValues = {"DEBITO", "CREDITO", "PIX", "DINHEIRO"}, example = "PIX")
    private String pagamento;

    // CORREÇÃO: Mapeamento N:1 para o Owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false) // Usando owner_id como nome da coluna
    @Schema(description = "Proprietário (Owner) que gerencia o agendamento")
    private Owner owner;
    // O campo 'private int cliente_usuario_id;' foi removido.

    // Campos extras (opcional) - Removidos para refletir o mapeamento JPA,
    // pois eles não são persistidos, mas obtidos via joins.

    /* @Schema(description = "Nome do Pet (campo extra para exibição)")
    private String petNome;
    @Schema(description = "Nome do Serviço (campo extra para exibição)")
    private String servicoNome;
    @Schema(description = "Nome do Funcionário (campo extra para exibição)")
    private String funcionarioNome;
    */


}