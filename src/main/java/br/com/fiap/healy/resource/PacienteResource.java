package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.PacienteRequest;
import br.com.fiap.healy.dto.response.PacienteResponse;
import br.com.fiap.healy.entity.Paciente;
import br.com.fiap.healy.service.HistoricoMedicoService;
import br.com.fiap.healy.service.PacienteService;
import br.com.fiap.healy.service.PessoaService;
import br.com.fiap.healy.service.PlanoSaudeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteResource {
    @Autowired
    private PacienteService service;

    @GetMapping
    public Collection<PacienteResponse> findAll(
            @RequestParam(name = "userPaciente", required = false) String userPaciente,
            @RequestParam(name = "cpf", required = false) String cpf
    ){
        Paciente paciente = Paciente.builder()
                .userPaciente(userPaciente)
                .cpf(cpf)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Paciente> example = Example.of(paciente, matcher);

        var entity = service.findAll(example);

        return entity.stream().map(service::toResponse).toList();

    }

    @GetMapping(value = "/{id}")
    public PacienteResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PacienteResponse> save(@RequestBody @Valid PacienteRequest pacienteRequest) {

        var entity = service.toEntity(pacienteRequest);

        var saved = service.save(entity);

        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved)
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }


}
