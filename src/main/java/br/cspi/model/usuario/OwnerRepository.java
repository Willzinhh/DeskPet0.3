package br.cspi.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query(value = "SELECT u.id as id, u.uuid as uuid, u.nome as nome, u.cpf as cpf, u.cnpj as cnpj, u.telefone as telefone, u.endereco as endereco, u.plano as plano, u.nome_empresa as nome_empresa " +
            "FROM owner u where u.id =:owner_id", nativeQuery = true)
    Owner findOwnerById(@Param("owner_id") Long owner_id);

    Owner findByCpf(String cpf);
}
