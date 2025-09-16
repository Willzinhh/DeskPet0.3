package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Column(name = "senha_hash")
    private String senha;

    @NotBlank
    private boolean ativo;

    // Relacionamento com Cliente_Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id") // nome da coluna FK no BD
    @NotBlank
    @JsonBackReference
    private Owner owner;

}
