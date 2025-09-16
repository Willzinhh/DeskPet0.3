package br.cspi.model.usuario;

import br.cspi.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.id as id, u.nome as nome, u.email as email, u.senha_hash as senha, u.ativo as ativo" +
            "FROM user u where a.owner_id =:id", nativeQuery = true)
    List<UserDTO> findUserByOwnerId(@Param("id") Long id);

}
