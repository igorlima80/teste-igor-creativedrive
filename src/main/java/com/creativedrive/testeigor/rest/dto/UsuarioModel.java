package com.creativedrive.testeigor.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UsuarioModel {
    private String id;
    private String username;
    private String password;
    private boolean isAdmin;
    private String nome;
    private String endereco;
    private String telefone;
    private String perfil;


    public String getPerfil() {
        if(this.isAdmin){
            perfil = "ADMIN";

        }else if (!this.isAdmin){
            perfil = "USER";
        }
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}