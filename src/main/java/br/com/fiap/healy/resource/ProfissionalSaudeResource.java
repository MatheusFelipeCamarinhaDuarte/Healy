package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.ProfissionalSaudeRequest;
import br.com.fiap.healy.dto.response.ProfissionalSaudeResponse;
import br.com.fiap.healy.entity.ProfissionalSaude;
import br.com.fiap.healy.service.PacienteService;
import br.com.fiap.healy.service.PessoaService;
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

@RestController
@RequestMapping(value = "/profissional-saude")
public class ProfissionalSaudeResource {
    @Autowired
    private ProfissionalSaudeService service;

    @GetMapping
    public Collection<ProfissionalSaudeResponse> findAll(
            @RequestParam(name = "userMedico", required = false) String userMedico,
            @RequestParam(name = "crm", required = false) String crm
    ){
        ProfissionalSaude profissionalSaude = ProfissionalSaude.builder()
                .userMedico(userMedico)
                .crm(crm)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<ProfissionalSaude> example = Example.of(profissionalSaude,matcher);

        var entity = service.findAll(example);

        return entity.stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public ProfissionalSaudeResponse findById(@PathVariable Long id){
        return  service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProfissionalSaudeResponse> save(@RequestBody @Valid ProfissionalSaudeRequest profissionalSaudeRequest){
        var entity = service.toEntity(profissionalSaudeRequest);

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
