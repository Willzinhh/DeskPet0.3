package br.cspi.model.agendamento;

import br.cspi.model.cliente.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    @Query(value = "SELECT u.id as id, u.data as data, u.horario as horario, u.observacao as observacao, u.status as status, u.pagamento as pagamento,u.pet_id as pet_id, u.servico_id as servico_id, u.funcionario_id as funcionario_id, u.owner_id as owner_id " +
            "FROM agendamento u where u.owner_id =:owner_id", nativeQuery = true)
    List<Agendamento> findAgendamentosByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.data as data, u.horario as horario, u.observacao as observacao, u.status as status, u.pagamento as pagamento,u.pet_id as pet_id, u.servico_id as servico_id, u.funcionario_id as funcionario_id, u.owner_id as owner_id " +
            "FROM agendamento u WHERE u.id = :agendamento_id AND u.owner_id = :owner_id", nativeQuery = true)
    Agendamento findAgendamentoByOwnerAndId(@Param("owner_id") Long owner_id, @Param("agendamento_id") Long agendamento_id);

    void deleteById(Long id);
}
