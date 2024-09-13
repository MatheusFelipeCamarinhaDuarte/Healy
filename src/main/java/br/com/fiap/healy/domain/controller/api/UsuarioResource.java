package br.com.fiap.healy.domain.controller.api;

import br.com.fiap.healy.domain.dto.request.UsuarioRequest;
import br.com.fiap.healy.domain.dto.response.UsuarioResponse;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource implements ResourceDTO<UsuarioRequest, UsuarioResponse>{
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(
            @RequestParam(name = "usuario",required = false) String usuario,
            @RequestParam(name = "pessoa.nome",required = false) String nome,
            @RequestParam(name = "pessoa.email",required = false) String email
    ){
        Pessoa pessoa = Pessoa.builder()
                .nome(nome)
                .email(email)
                .build();
        Usuario user = Usuario.builder()
                .user(usuario)
                .pessoa(pessoa)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();
        Example<Usuario> example = Example.of(user,matcher);
        var entities = service.findAll(example);
        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
        var entity = service.toEntity(r);
        entity = service.save(entity);
        var response = service.toResponse(entity);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
