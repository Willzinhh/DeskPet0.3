package br.cspi.model.pet;

import br.cspi.model.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "SELECT u.id as id, u.nomepet as nomepet, u.especie as especie, u.raca as raca, u.sexo as sexo, u.descricao as descricao, u.data_cricao as data_cricao, u.tutor_id as tutor_id, u.owner_id as  owner_id " +
            "FROM pet u where u.owner_id =:owner_id", nativeQuery = true)
    List<Pet> findPetsByOwner(@Param("owner_id") Long owner_id);

    @Query( value = "SELECT u.id as id, u.nomepet as nomepet, u.especie as especie, u.raca as raca, u.sexo as sexo, u.descricao as descricao, u.data_cricao as data_cricao, u.tutor_id as tutor_id, u.owner_id as  owner_id " +
            "FROM pet u WHERE u.id = :pet_id AND u.owner_id = :owner_id", nativeQuery = true)
    Pet findPetByOwnerAndId(@Param("owner_id") Long owner_id, @Param("pet_id") Long pet_id);


    @Query( value = "SELECT u.id as id, u.nomepet as nomepet, u.especie as especie, u.raca as raca, u.sexo as sexo, u.descricao as descricao, u.data_cricao as data_cricao, u.tutor_id as tutor_id, u.owner_id as  owner_id " +
            "FROM pet u WHERE u.tutor_id = :tutor_id AND u.owner_id = :owner_id", nativeQuery = true)
    List<Pet> findPetByOwnerAndTutor(@Param("owner_id") Long owner_id, @Param("tutor_id") Long tutor_id);
}
