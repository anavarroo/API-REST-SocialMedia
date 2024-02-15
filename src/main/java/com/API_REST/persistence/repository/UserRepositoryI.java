package com.API_REST.persistence.repository;

/*
	Repositorio de T_USER
	@author AlbertoNavarro
 */

import com.API_REST.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepositoryI extends JpaRepository<User,Long> {


    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.description = :description WHERE u.username = :username")
    void updateDescription(@Param("username") String username, @Param("description") String description);


}
