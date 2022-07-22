package com.creativedrive.testeigor.core;


import com.creativedrive.testeigor.model.entity.Usuario;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioUtil{


	
	public static Usuario criarUsuario(String email){
        Usuario usuario = new Usuario();
        usuario.setUsername(email);
        usuario.setPassword(UsuarioUtil.generateCommonLangPassword());
        usuario.setIsAdmin(false);
        //usuario.setStatus(StatusUsuario.ATUALIZAR_SENHA);

        return usuario;
    }
	
	public static Usuario criarUsuarioAdmin(String email){
        Usuario usuario = new Usuario();
        usuario.setUsername(email);
        usuario.setPassword(UsuarioUtil.generateCommonLangPassword());
        usuario.setIsAdmin(true);
        //usuario.setStatus(StatusUsuario.ATUALIZAR_SENHA);

        return usuario;
    }
	
	public static String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
          .concat(numbers)
          .concat(specialChar)
          .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
          .mapToObj(c -> (char) c)
          .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
          .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
          .toString();
        return password;
    }



}
