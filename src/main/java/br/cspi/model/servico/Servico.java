package br.cspi.model.servico;

import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.usuario.Owner; // Import necessário
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes de um Serviço oferecido pela clínica/pet shop.")
public class Servico {

    // ... Campos de dados do Serviço ...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Serviço", example = "3")
    private int id;
    @Schema(description = "Nome do Serviço", example = "Banho e Tosa")
    private String nome;
    @Schema(description = "Descrição detalhada do Serviço", example = "Inclui corte de pelo, limpeza de ouvidos e corte de unhas.")
    private String descricao;
    @Schema(description = "Valor cobrado pelo Serviço", example = "85.00")
    private double valor;
    @Schema(description = "Tempo médio de duração do Serviço (em formato 'HH:MM')", example = "01:30")
    private String tempo;

    // CORREÇÃO: Mapeamento N:1 para o Owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @Schema(description = "Proprietário (Owner) que gerencia o registro")
    private Owner owner;
    // O campo 'private int cliente_usuario_id;' foi removido.

    // Mapeamento N:N (Já estava correto!)
    @ManyToMany(mappedBy = "servicos")
    @JsonBackReference
    @Schema(description = "Lista de funcionários habilitados para realizar este serviço")
    private Set<Funcionario> funcionarios = new HashSet<>();
}