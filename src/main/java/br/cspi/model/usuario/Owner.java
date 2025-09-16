package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UuidGenerator
    private UUID uuid;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 14, max = 14, message = "CPF deve seguir o formato XXX.XXX.XXX-XX ")
    private String cpf;

    @Size(min = 18, max = 18, message = "CNPJ deve seguir o formato XX.XXX.XXX/0001-AA")
    private String cnpj;

    @NotBlank
    @Size(min = 14, max = 14, message = "Telefone deve seguir o formato (XX)XXXXX-XXXX")
    private String telefone;

    @NotBlank
    private String endereco;

    private String nome_empresa;

    @NotBlank
    @Pattern(regexp = "^(Free|Basic|Premium)$",
            message = "O plano deve ser 'Free', 'Basic' ou 'Premium'.")
    private String plano;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
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

