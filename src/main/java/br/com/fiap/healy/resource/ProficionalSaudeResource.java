package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.ProficionalSaudeRequest;
import br.com.fiap.healy.dto.response.ProficionalSaudeResponse;
import br.com.fiap.healy.entity.ProficionalSaude;
import br.com.fiap.healy.service.PacienteService;
import br.com.fiap.healy.service.PessoaService;
import br.com.fiap.healy.service.ProficionalSaudeService;
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
@RequestMapping(value = "/proficional-saude")
public class ProficionalSaudeResource {
    @Autowired
    private ProficionalSaudeService service;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public Collection<ProficionalSaudeResponse> findAll(
            @RequestParam(name = "userProficionalSaude") String userProficional,
            @RequestParam(name = "crm") String crm
    ){
        ProficionalSaude proficionalSaude = ProficionalSaude.builder()
                .userMedico(userProficional)
                .crm(crm)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<ProficionalSaude> example = Example.of(proficionalSaude,matcher);

        var entity = service.findAll(example);

        return entity.stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public ProficionalSaudeResponse findById(@PathVariable Long id){
        return  service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProficionalSaudeResponse> save(@RequestBody @Valid ProficionalSaudeRequest proficionalSaudeRequest){
        var entity = service.toEntity(proficionalSaudeRequest);

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
