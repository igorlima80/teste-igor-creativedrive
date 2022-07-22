package com.creativedrive.testeigor.config;

import com.creativedrive.testeigor.model.entity.Usuario;
import com.creativedrive.testeigor.model.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UsuarioRepository.class)
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunner( UsuarioRepository usuarioRepository){
        return strings ->{
            usuarioRepository.save(new Usuario("1", "username1", "senha1", false, "nome1", "endereco1", "telefone1"));
            usuarioRepository.save(new Usuario("2", "username2", "senha2", false, "nome2", "endereco2", "telefone2"));
            usuarioRepository.save(new Usuario("3", "username3", "senha3", true, "nome3", "endereco3", "telefone3"));
        };
    }
}
