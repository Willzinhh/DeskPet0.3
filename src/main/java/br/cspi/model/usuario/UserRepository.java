package br.cspi.model.usuario;

import br.cspi.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    

    @Query(value = "SELECT u.id as id, u.nome as nome, u.email as email, u.senha as senha, u.permissao as permissao, u.owner_id " +
            "FROM usuario u where u.owner_id =:owner_id", nativeQuery = true)
    List<User> findUserByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.nome as nome, u.email as email, u.senha as senha, u.permissao as permissao, u.owner_id " +
            "FROM usuario u WHERE u.id = :user_id AND u.owner_id = :owner_id", nativeQuery = true)
    User findUserByOwnerAndId(@Param("owner_id") Long owner_id, @Param("user_id") Long user_id);


    User findByEmail(String login);
}

