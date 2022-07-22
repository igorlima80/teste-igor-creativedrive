package com.creativedrive.testeigor;

import com.creativedrive.testeigor.model.entity.Usuario;
import com.creativedrive.testeigor.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteIgorApplication {

	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(TesteIgorApplication.class, args);
	}


}
