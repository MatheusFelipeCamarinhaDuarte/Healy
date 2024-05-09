package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.HistoricoMedicoRequest;
import br.com.fiap.healy.dto.response.HistoricoMedicoResponse;
import br.com.fiap.healy.entity.HistoricoMedico;
import br.com.fiap.healy.service.HistoricoMedicoService;
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
@RequestMapping(value = "/historico-medico")
public class HistoricoMedicoResource {
    @Autowired
    private HistoricoMedicoService service;

    @GetMapping
    public ResponseEntity<Collection<HistoricoMedicoResponse>> findAll(
            @RequestParam(name = "doecas", required = false) String doecas,
            @RequestParam(name = "doencasAnteriores", required = false) String doencasAnteriores,
            @RequestParam(name = "alergias", required = false) String alergias,
            @RequestParam(name = "medicamento", required = false) String medicamento
    ) {

        HistoricoMedico historicoMedico = HistoricoMedico.builder()
                .doencas(doecas)
                .doencasAnteriores(doencasAnteriores)
                .alergias(alergias)
                .medicamento(medicamento)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();
        Example<HistoricoMedico> example = Example.of(historicoMedico, matcher);

        var entities = service.findAll(example);

        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HistoricoMedicoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HistoricoMedicoResponse> save(@RequestBody @Valid HistoricoMedicoRequest historicoMedicoRequest) {
        var entity = service.toEntity(historicoMedicoRequest);

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