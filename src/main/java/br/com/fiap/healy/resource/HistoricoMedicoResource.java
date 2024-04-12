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

@RestController
@RequestMapping(value = "/historico-medico")
public class HistoricoMedicoResource {
    @Autowired
    private HistoricoMedicoService service;

    @GetMapping
    public Collection<HistoricoMedicoResponse> findAll(
            @RequestParam(name = "doecas",required = false) String doecas,
            @RequestParam(name = "doencasAnteriores",required = false) String doencasAnteriores,
            @RequestParam(name = "alergias",required = false) String alergias,
            @RequestParam(name = "medicamento",required = false) String medicamento
    ){

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

        Example<HistoricoMedico> example = Example.of(historicoMedico,matcher);

        return service.findAll(example).stream().map(service::toResponse).toList();

    }
    @GetMapping(value = "/{id}")
    public HistoricoMedicoResponse findById(@PathVariable Long id){
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HistoricoMedicoResponse> save(@RequestBody @Valid HistoricoMedicoRequest historicoMedicoRequest){
        var saved = service.save(service.toEntity(historicoMedicoRequest));

        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved)
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}