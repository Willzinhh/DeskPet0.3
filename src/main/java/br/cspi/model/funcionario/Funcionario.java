package br.cspi.model.funcionario;

import br.cspi.model.servico.Servico;
import br.cspi.model.usuario.Owner; // Import necessário
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes de um Funcionário (Usuário) da clínica/pet shop.")
public class Funcionario {

    // ... Campos de dados do Funcionário ...
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Funcionário", example = "5")
    private int id;
    @Schema(description = "Nome completo do Funcionário", example = "Ana Costa")
    private String nome;
    @Schema(description = "CPF do Funcionário", example = "321.654.987-00")
    private String cpf;
    @Schema(description = "Telefone para contato", example = "(11)99999-8888")
    private String telefone;
    @Schema(description = "Cargo ou função do Funcionário", example = "Veterinário")
    private String cargo;
    @Schema(description = "Salário ou base salarial do Funcionário", example = "3500.00")
    private BigDecimal salario;
    @Schema(description = "Status de atividade do Funcionário", example = "true")
    private boolean ativo;

    // CORREÇÃO: Mapeamento N:1 para o Owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false) // Usando owner_id como nome da coluna
    @Schema(description = "Proprietário (Owner) que gerencia o registro")
    private Owner owner;
    // O campo 'private int cliente_usuario_id;' foi removido.

    // Mapeamento N:N (Já estava correto!)
    @ManyToMany
    @JoinTable(
            name = "funcionario_servico",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    @JsonManagedReference
    @Schema(description = "Lista de serviços que o funcionário está habilitado a realizar")
    private Set<Servico> servicos = new HashSet<>();

    public void addServico(Servico servico) {
        this.servicos.add(servico);
        servico.getFuncionarios().add(this);
    }
}