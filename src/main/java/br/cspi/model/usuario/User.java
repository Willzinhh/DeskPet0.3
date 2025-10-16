package br.cspi.model.usuario;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="usuario")
@Schema(description = "Detalhes do Usuário Secundário (Funcionário) vinculado a um Proprietário")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do Usuário", example = "10")
    private Long id;

    @NotBlank
    @Schema(description = "Nome completo do Usuário", example = "Pedro Souza")
    private String nome;

    @Email
    @NotBlank
    @Schema(description = "Login/Email do Usuário", example = "pedro.souza@empresa.com.br")
    private String email;

    @Schema(description = "Senha do Usuário", writeOnly = true) // writeOnly para não expor no GET
    private String senha;

    private String permissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    @Schema(description = "Proprietário (Owner) ao qual este Usuário está vinculado")
    private Owner owner;

    // Construtores omitidos para brevidade
}
