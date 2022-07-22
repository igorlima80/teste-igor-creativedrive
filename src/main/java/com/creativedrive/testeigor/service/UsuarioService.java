package com.creativedrive.testeigor.service;



import com.creativedrive.testeigor.model.entity.Usuario;
import com.creativedrive.testeigor.model.repository.UsuarioRepository;
import com.creativedrive.testeigor.rest.dto.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;



    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository
                                .findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Login Não Encontrado."));

        UsuarioModel model = toUsuarioModel(usuario);
        if(usuario.getIsAdmin()){
            model.setPerfil("ADMIN");

        }else if (!usuario.getIsAdmin()){
            model.setPerfil("USER");
        }

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(model.getPerfil())
                .build();
    }

    public List<UsuarioModel> listAll(){
        return repository
                .findAll()
                .stream()
                .map(this::toUsuarioModel)
                .collect(Collectors.toList());
    }

    public Usuario save(Usuario usuario) {
        try {
            repository.save(usuario);

            return usuario;

        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public UsuarioModel find(String id) {
        Usuario usuario = repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Não Encontrado."));

        return toUsuarioModel(usuario);
    }

    public void delete(String id) {
        repository
                .findById(id)
                .map(usuario -> {
                    repository.delete(usuario);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Não Encontrado."));
    }

    public ResponseEntity<Usuario> update(String id, Usuario usuario) {
        return repository.findById(id)
                .map(record -> {
                    record.setUsername(usuario.getUsername());
                    record.setPassword(usuario.getPassword());
                    record.setNome(usuario.getNome());
                    record.setEndereco(usuario.getEndereco());
                    record.setTelefone(usuario.getTelefone());
                    record.setIsAdmin(usuario.getIsAdmin());

                    Usuario updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public List<UsuarioModel> findUsuariosByProperties(String nome, String username, String telefone, String ordem, String direcao, Integer page){

        return repository
                .findUsuariosByProperties(nome, username, telefone, ordem, direcao, PageRequest.of(page, 15))
                .stream()
                .map(this::toUsuarioModel)
                .collect(Collectors.toList());
    }

    public UsuarioModel toUsuarioModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    private Usuario toUsuario(UsuarioModel user){
        return modelMapper.map(user, Usuario.class);
    }
    
   
}
