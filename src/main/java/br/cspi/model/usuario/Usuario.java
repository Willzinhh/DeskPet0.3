package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private String email;

    @NonNull
    @Column(name = "senha_hash")
    private String senha;

    @NonNull
    private boolean ativo;

    // Relacionamento com Cliente_Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_usuario_id") // nome da coluna FK no BD
    @NonNull
    @JsonBackReference
    private Cliente_Usuario cliente;

}
