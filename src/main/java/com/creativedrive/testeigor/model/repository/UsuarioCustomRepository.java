package com.creativedrive.testeigor.model.repository;


import com.creativedrive.testeigor.model.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioCustomRepository{
    public List<Usuario> findUsuariosByProperties(String nome, String username, String telefone, String ordem, String direcao, Pageable page);
}
