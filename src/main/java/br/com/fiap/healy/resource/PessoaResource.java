package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.PessoaRequest;
import br.com.fiap.healy.dto.response.PessoaResponse;
import br.com.fiap.healy.entity.Pessoa;
import br.com.fiap.healy.service.PessoaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "nascimento", required = false) LocalDate nascimento,
            @RequestParam(name = "email", required = false) String email
    ) {

        Pessoa pessoa = Pessoa.builder()
                .nome(nome)
                .nascimento(nascimento)
                .email(email)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Pessoa> example = Example.of(pessoa, matcher);

        var entities = service.findAll(example);

        var response = entities.stream().map(service::toResponse).toList();
        return  ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PessoaResponse> save(@RequestBody @Valid PessoaRequest pessoa) {
        var entity = service.toEntity(pessoa);

        entity = service.save(entity);

        var response = service.toResponse(entity);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


}
