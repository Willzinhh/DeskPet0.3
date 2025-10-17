package br.cspi.dto;

public interface UserDTO {
    Long getId();
    String getNome();
    String getEmail();
    String getSenha();
    Boolean isAtivo();
    String getPermissao();

}
