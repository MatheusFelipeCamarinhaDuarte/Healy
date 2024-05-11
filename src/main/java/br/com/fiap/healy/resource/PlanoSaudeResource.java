package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.PlanoSaudeRequest;
import br.com.fiap.healy.dto.response.PlanoSaudeResponse;
import br.com.fiap.healy.service.PlanoSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping(value = "/plano-saude")
public class PlanoSaudeResource {

    @Autowired
    private PlanoSaudeService service;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;


    @GetMapping
    public ResponseEntity<List<PlanoSaudeResponse>> findAll() {

        var entities = service.findAll();

        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlanoSaudeResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response =service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PlanoSaudeResponse> save(@RequestBody PlanoSaudeRequest planoSaudeRequest) {

        var entity = service.toEntity(planoSaudeRequest);

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
