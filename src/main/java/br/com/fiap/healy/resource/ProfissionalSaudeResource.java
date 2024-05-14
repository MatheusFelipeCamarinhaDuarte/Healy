package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.ProfissionalSaudeRequest;
import br.com.fiap.healy.dto.response.ProfissionalSaudeResponse;
import br.com.fiap.healy.entity.ProfissionalSaude;
import br.com.fiap.healy.service.ProfissionalSaudeService;
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
@RequestMapping(value = "/profissional-saude")
public class ProfissionalSaudeResource {
    // TODO: Ajeitar a classe ProfissionalSaudeResource para conter as novas modificações

    @Autowired
    private ProfissionalSaudeService service;

    @GetMapping
    public ResponseEntity<Collection<ProfissionalSaudeResponse>> findAll(){
        ProfissionalSaude profissionalSaude = ProfissionalSaude.builder()

                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<ProfissionalSaude> example = Example.of(profissionalSaude,matcher);

        var entities = service.findAll(example);

        var response = entities.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProfissionalSaudeResponse> save(@RequestBody @Valid ProfissionalSaudeRequest profissionalSaudeRequest){
        var entity = service.toEntity(profissionalSaudeRequest);
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
