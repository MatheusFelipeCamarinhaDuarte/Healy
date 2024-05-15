package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.TelefoneRequest;
import br.com.fiap.healy.dto.response.TelefoneResponse;
import br.com.fiap.healy.entity.Pessoa;
import br.com.fiap.healy.entity.Telefone;
import br.com.fiap.healy.service.TelefoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/telefone")
public class TelefoneResource implements ResourceDTO<TelefoneRequest, TelefoneResponse>{
    @Autowired
    private TelefoneService service;
    @Autowired
    private ServletWebServerFactoryAutoConfiguration servletWebServerFactoryAutoConfiguration;

    @GetMapping
    public ResponseEntity<Collection<TelefoneResponse>> findAll(
            @RequestParam(name = "ddi",required = false) String ddi,
            @RequestParam(name = "ddd",required = false) String ddd,
            @RequestParam(name = "numero",required = false) String numero,
            @RequestParam(name = "pessoa.nome",required = false) String nmPessoa
    ){
        Pessoa pessoa = Pessoa.builder()
                .nome(nmPessoa)
                .build();
        Telefone telefone = Telefone.builder()
                .ddd(ddd)
                .ddi(ddi)
                .numero(numero)
                .pessoa(pessoa)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<Telefone> example = Example.of(telefone,matcher);
        var entity = service.findAll(example);
        var response = entity.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<TelefoneResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<TelefoneResponse> save(@RequestBody @Valid TelefoneRequest r) {
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
