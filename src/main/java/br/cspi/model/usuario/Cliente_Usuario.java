package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@RequiredArgsConstructor
@Table(name = "cliente_usuario")
public class Cliente_Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    private String cnpj;

    @NonNull
    private String telefone;

    @NonNull
    private String endereco;

    private String nome_empresa;

    @NonNull
    private String plano;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private List<Usuario> usuarios = new ArrayList<>();

    // Método auxiliar para manter a consistência do relacionamento
    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.setCliente(this);
    }

    public void removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.setCliente(null);
    }
}

