package br.cspi.model.usuario;

import br.cspi.dto.UserDTO;

import java.util.Optional;

public record DadosUser(Long id, String nome, String email, String permissao) {
    public DadosUser(User user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getPermissao());
    }



}
