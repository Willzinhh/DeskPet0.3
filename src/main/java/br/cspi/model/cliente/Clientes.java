package br.cspi.model.cliente;

import br.cspi.model.pet.Pet;
import br.cspi.model.usuario.Owner; // Import necessário
import br.cspi.model.usuario.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detalhes do Cliente (Tutor)")
public class Clientes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Cliente", example = "5" )
    private Long id;

    // ... Campos de dados do cliente ...
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

    // CORREÇÃO: Mapeamento N:1 para o Owner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)

    @Schema(description = "Proprietário (Owner) que gerencia este Cliente", hidden = true)
    private Owner owner;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference

    @Schema(description = "Lista de Pets vinculados a este Cliente",
            accessMode = Schema.AccessMode.READ_ONLY)
    private List<Pet> pets = new ArrayList<>();

    // Método auxiliar para manter a consistência do relacionamento
    public void addPet(Pet pet) {
        pet.setOwner(this.owner);
        pet.setTutor(this);
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
    }


}