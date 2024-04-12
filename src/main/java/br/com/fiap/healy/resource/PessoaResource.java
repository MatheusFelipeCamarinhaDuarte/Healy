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

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<PessoaResponse> findAll(
            @RequestParam(name="nome", required=false) String nome,
            @RequestParam(name="nascimento", required = false)LocalDate nascimento,
            @RequestParam(name="email",required = false) String email
    ) {

        Pessoa pessoa = Pessoa.builder()
                .nome( nome )
                .nascimento( nascimento )
                .email( email )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Pessoa> example = Example.of(pessoa, matcher);

        var entity = service.findAll( example );

        return entity.stream().map( service::toResponse ).toList();
    }

    @GetMapping(value = "/{id}")
    public PessoaResponse findById(@PathVariable Long id) {
        return service.toResponse( service.findById( id ) );
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PessoaResponse> save(@RequestBody @Valid PessoaRequest pessoa) {
        var entity = service.toEntity( pessoa );

        var saved = service.save( entity );

        var response = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


}
