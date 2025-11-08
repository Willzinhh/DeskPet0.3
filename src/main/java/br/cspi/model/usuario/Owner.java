package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema; // Import adicionado
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
@Schema(description = "Detalhes do Proprietário (Dono da conta principal)") // Anotação de classe
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Proprietário", example = "1") // Anotação de campo
    private long id;

    @UuidGenerator
    @Schema(description = "UUID único gerado automaticamente", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID uuid;

    @NotBlank
    @Schema(description = "Nome completo do Proprietário", example = "João da Silva")
    private String nome;

    @Column(unique = true)
    @NotBlank
    @Size(min = 14, max = 14, message = "CPF deve seguir o formato XXX.XXX.XXX-XX ")
    @Schema(description = "CPF do Proprietário no formato XXX.XXX.XXX-XX", example = "123.456.789-00")
    private String cpf;

    @Column(unique = true)
    @Size(min = 18, max = 18, message = "CNPJ deve seguir o formato XX.XXX.XXX/0001-AA")
    @Schema(description = "CNPJ da empresa no formato XX.XXX.XXX/0001-AA (Opcional)", example = "11.222.333/0001-44", nullable = true)
    private String cnpj;

    @Column(unique = true)
    @NotBlank
    @Size(min = 14, max = 14, message = "Telefone deve seguir o formato (XX)XXXXX-XXXX")
    @Schema(description = "Telefone para contato no formato (XX)XXXXX-XXXX", example = "(11)98765-4321")
    private String telefone;

    @NotBlank
    @Schema(description = "Endereço completo do Proprietário", example = "Rua Teste, 123 - Centro")
    private String endereco;

    @Schema(description = "Nome da empresa (Opcional)", example = "PetShop Fantasia", nullable = true)
    private String nome_empresa;

    @NotBlank
    @Pattern(regexp = "^(Free|Basic|Premium)$",
            message = "O plano deve ser 'Free', 'Basic' ou 'Premium'.")
    @Schema(description = "Plano de assinatura do Proprietário", allowableValues = {"Free", "Basic", "Premium"}, example = "Premium")
    private String plano;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @Schema(description = "Lista de Usuários (funcionários) vinculados a este Proprietário",
            accessMode = Schema.AccessMode.READ_ONLY)
    private List<User> users = new ArrayList<>();

    // Método auxiliar para manter a consistência do relacionamento
    public void addUser(User user) {
        users.add(user);
        user.setOwner(this);
    }

    public void removeUsuario(User user) {
        users.remove(user);
        user.setOwner(null);
    }

}