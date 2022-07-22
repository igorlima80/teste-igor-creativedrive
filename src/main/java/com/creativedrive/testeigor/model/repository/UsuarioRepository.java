package com.creativedrive.testeigor.model.repository;


import com.creativedrive.testeigor.model.entity.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String>, UsuarioCustomRepository {

    Optional<Usuario> findByUsername(String username);
    //Page<Usuario> findByName(String username, Pageable pageable);
    //Page<Usuario> findAll(Pageable pageable);

    boolean existsByUsername(String username);

    @Query("{id:'?0'}")
    Optional<Usuario> findById(String id);



}
