package br.cspi.model.usuario;

public record DadosUser(Long id, String nome, String email, String permissao) {
    public DadosUser(User user){
        this(user.getId(), user.getNome(), user.getEmail(), user.getPermissao());
    }
}
