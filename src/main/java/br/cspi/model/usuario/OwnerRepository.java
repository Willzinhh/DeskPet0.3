package br.cspi.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query(value = "SELECT o.id as id, o.nome as nome, o.cpf as cpf, o.cnpj as cnpj, o.telefone as telefone, o.endereco as endereco, o.nome_empresa as nome_empresa, o.plano as plano" +
            " FROM owner o where o.user")
}
