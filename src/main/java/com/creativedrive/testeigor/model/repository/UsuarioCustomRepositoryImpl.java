package com.creativedrive.testeigor.model.repository;

import com.creativedrive.testeigor.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioCustomRepositoryImpl implements UsuarioCustomRepository{

    @Autowired
    MongoTemplate mongoTemplate;


    public List<Usuario> findUsuariosByProperties(String nome, String username, String telefone, String ordem, String direcao, Pageable page) {
        final Query query = new Query().with(page);
        final List<Criteria> criteria = new ArrayList<>();

        if (nome != null && !nome.isEmpty())
            criteria.add(Criteria.where("nome").regex(".*" + nome + ".*", "i"));

        if (username != null && !username.isEmpty())
            criteria.add(Criteria.where("username").regex(".*" + username + ".*", "i"));

        if (telefone != null && !telefone.isEmpty())
            criteria.add(Criteria.where("telefone").regex(".*" + telefone + ".*", "i"));

        if (ordem != null && !ordem.isEmpty()){
            if(direcao == null || direcao.isEmpty()) {
                query.with(Sort.by(Sort.Direction.ASC, ordem));
            }else if(direcao.equals("ASC")){
                        query.with(Sort.by(Sort.Direction.ASC, ordem));
                    } else if(direcao.equals("DESC")){
                        query.with(Sort.by(Sort.Direction.DESC, ordem));
                    }else{
                        query.with(Sort.by(Sort.Direction.ASC, ordem));
                    }
        }


        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));



        return mongoTemplate.find(query, Usuario.class);
    }
}
