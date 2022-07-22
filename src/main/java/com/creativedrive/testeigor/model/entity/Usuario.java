package com.creativedrive.testeigor.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("usuarios")
public class Usuario {
    @Id
    private String id;

    private String username;

    private String password;

    private Boolean isAdmin;

    private String nome;

    private String endereco;

    private String telefone;

}
