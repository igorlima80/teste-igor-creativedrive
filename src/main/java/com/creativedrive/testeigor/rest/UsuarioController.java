package com.creativedrive.testeigor.rest;

import com.creativedrive.testeigor.model.entity.Usuario;
import com.creativedrive.testeigor.model.repository.UsuarioRepository;
import com.creativedrive.testeigor.rest.dto.UsuarioModel;
import com.creativedrive.testeigor.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Getter
@Setter
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @ApiOperation(value = "Busca o Usuário Logado")
    @RequestMapping(value = "/logado", method = RequestMethod.GET)
    @ResponseBody
    public UsuarioModel currentUser(Authentication authentication) {
        return repository
                .findByUsername(authentication.getName())
                .map(usuario -> {
                    UsuarioModel model = service.toUsuarioModel(usuario);
                    if(usuario.getIsAdmin()){
                        model.setPerfil("ADMIN");

                    }else if (!usuario.getIsAdmin()){
                        model.setPerfil("USER");
                    }

                    return model;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Não Encontrado."));
    }

    @ApiOperation(value = "Cadastra um Usuário")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario){
        if(repository.existsByUsername(usuario.getUsername()) == true){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O E-mail informado já existe no banco de dados");
        }

        return service.save(usuario);
    }

    @ApiOperation(value = "Busca um Usuário por ID")
    @GetMapping("{id}")
    public UsuarioModel buscarPorId(@PathVariable String id){
        return service.find(id);
    }

    @ApiOperation(value = "Deleta um Usuário por ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable String id){
        service.delete(id);
    }

    @ApiOperation(value = "Altera um Usuário")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Usuario> atualizar(@PathVariable("id") String id, @RequestBody Usuario usuario){
        return service.update(id, usuario);
    }

/*
    @GetMapping
    public List<UsuarioModel> listarTodos(){
        return service.listAll();
    }
*/
    @ApiOperation(value = "Listar todos os Usuários, com filtragem por nome, username, telefone, paginação e ordenação")
    @GetMapping
    public ResponseEntity<?> getUsuariosByProperties(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String ordem,
            @RequestParam(required = false) String direcao,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ResponseEntity.ok(service.findUsuariosByProperties(nome, username, telefone, ordem, direcao, page));
    }

}
