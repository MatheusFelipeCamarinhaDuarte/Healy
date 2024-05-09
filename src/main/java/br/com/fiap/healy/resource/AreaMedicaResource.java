package br.com.fiap.healy.resource;


import br.com.fiap.healy.dto.request.AreaMedicaRequest;
import br.com.fiap.healy.dto.response.AreaMedicaResponse;
import br.com.fiap.healy.entity.AreaMedica;
import br.com.fiap.healy.service.AreaMedicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/area-medica")
public class AreaMedicaResource {
    @Autowired
    private AreaMedicaService service;

    @GetMapping
    public ResponseEntity<List<AreaMedicaResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        AreaMedica area = AreaMedica.builder()
                .nome(nome)
                .build();

        ExampleMatcher macther = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<AreaMedica> example = Example.of(area, macther);

        var entities = service.findAll(example);

        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AreaMedicaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();

        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AreaMedicaResponse> save(@RequestBody @Valid AreaMedicaRequest area) {
        var entity = service.toEntity(area);

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
