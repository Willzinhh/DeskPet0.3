package br.cspi.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Clientes , Long> {

    @Query(value = "SELECT u.id as id, u.nome as nome, u.telefone as telefone, u.cpf as cpf, u.endereco as endereco, u.data_criacao as data_criacao, u.owner_id as owner_id " +
            "FROM clientes u where u.owner_id =:owner_id", nativeQuery = true)
    List<Clientes> findClientesByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.nome as nome, u.telefone as telefone, u.cpf as cpf, u.endereco as endereco, u.data_criacao as data_criacao, u.owner_id as owner_id " +
            "FROM clientes u WHERE u.id = :cliente_id AND u.owner_id = :owner_id", nativeQuery = true)
    Clientes findClienteByOwnerAndId(@Param("owner_id") Long owner_id, @Param("cliente_id") Long cliente_id);


}
