package br.cspi.model.servico;

import br.cspi.model.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query(value = "SELECT u.id as id, u.nome as nome, u.descricao as descricao, u.valor as valor, u.tempo as tempo, u.owner_id as owner_id " +
            "FROM servico u where u.owner_id =:owner_id", nativeQuery = true)
    List<Servico> findServicosByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.nome as nome, u.descricao as descricao, u.valor as valor, u.tempo as tempo, u.owner_id as owner_id " +
            "FROM servico u WHERE u.id = :servico_id AND u.owner_id = :owner_id", nativeQuery = true)
    Servico findServicoByOwnerAndId(@Param("owner_id") Long owner_id, @Param("servico_id") Long servico_id);

}
