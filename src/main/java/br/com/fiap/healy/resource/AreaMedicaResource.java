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

@RestController
@RequestMapping(value = "/area-medica")
public class AreaMedicaResource {
    @Autowired
    private AreaMedicaService service;

    @GetMapping
    public List<AreaMedicaResponse> findAll(
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

        var entity = service.findAll(example);

        return entity.stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public AreaMedicaResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AreaMedicaResponse> save(@RequestBody @Valid AreaMedicaRequest area) {
        var entity = service.toEntity(area);
        var saved = service.save(entity);
        var response = service.toResponse(saved);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


}
