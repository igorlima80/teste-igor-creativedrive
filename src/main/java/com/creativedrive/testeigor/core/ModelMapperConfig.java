package com.creativedrive.testeigor.core;

import com.creativedrive.testeigor.model.entity.Usuario;
import com.creativedrive.testeigor.rest.dto.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .createTypeMap(Usuario.class, UsuarioModel.class);


        return modelMapper;
    }

}
