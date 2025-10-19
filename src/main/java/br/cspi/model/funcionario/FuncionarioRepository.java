package br.cspi.model.funcionario;

import br.cspi.model.cliente.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT u.id as id, u.nome as nome, u.telefone as telefone, u.cpf as cpf, u.cargo as cargo, u.salario as salario, u.ativo as ativo, u.owner_id as owner_id " +
            "FROM funcionario u where u.owner_id =:owner_id", nativeQuery = true)
    List<Funcionario> findFuncionarioByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.nome as nome, u.telefone as telefone, u.cpf as cpf, u.cargo as cargo, u.salario as salario, u.ativo as ativo, u.owner_id as owner_id " +
            "FROM funcionario u WHERE u.id = :funcionario_id AND u.owner_id = :owner_id", nativeQuery = true)
    Funcionario findFuncionarioByOwnerAndId(@Param("owner_id") Long owner_id, @Param("funcionario_id") Long funcionario_id);

}

