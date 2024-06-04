package br.com.fiap.healy.domain.resource;

import br.com.fiap.healy.domain.dto.request.ExameRequest;
import br.com.fiap.healy.domain.dto.response.ExameResponse;
import br.com.fiap.healy.domain.entity.Exame;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.service.ExameService;
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
@RequestMapping(value = "/exames")
public class ExameResource implements ResourceDTO<ExameRequest, ExameResponse>{
    @Autowired
    private ExameService service;

    @GetMapping
    public ResponseEntity<Collection<ExameResponse>> findAll(
            @RequestParam(name = "sexo",required = false) String sexo,
            @RequestParam(name = "indiceMassa",required = false) Integer indiceMassa,
            @RequestParam(name = "mesAteCrise",required = false) Integer mesAteCrise,
            @RequestParam(name = "anoAteCrise",required = false) Integer anoAteCrise,
            @RequestParam(name = "pessoa.nome",required = false) String nmPessoa,
            @RequestParam(name = "pessoa.email", required = false) String email
    ){
        Pessoa pessoa = Pessoa.builder()
                .nome(nmPessoa)
                .email(email)
                .build();
        Exame exame = Exame.builder()
                .sexo(sexo)
                .indiceMassa(indiceMassa)
                .mesAteCrise(mesAteCrise)
                .anoAteCrise(anoAteCrise)
                .pessoa(pessoa)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();
        Example<Exame> example = Example.of(exame,matcher);
        var entities = service.findAll(example);
        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ExameResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<ExameResponse> save(@RequestBody @Valid ExameRequest r) {
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
