package br.com.fiap.healy.domain.controller.api;

import br.com.fiap.healy.domain.dto.request.ProfissionalSaudeRequest;
import br.com.fiap.healy.domain.dto.response.ProfissionalSaudeResponse;
import br.com.fiap.healy.domain.entity.DocumentoSaude;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.ProfissionalSaude;
import br.com.fiap.healy.domain.service.ProfissionalSaudeService;
import br.com.fiap.healy.domain.service.TelefoneService;
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
public class ProfissionalSaudeResource implements ResourceDTO<ProfissionalSaudeRequest, ProfissionalSaudeResponse>{
    @Autowired
    private ProfissionalSaudeService service;
    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<Collection<ProfissionalSaudeResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "documento.sigla", required = false) String sigla,
            @RequestParam(name = "documento.estado", required = false) String estado,
            @RequestParam(name = "documento.numero", required = false) String numero
    ){
        Pessoa pessoaMedico = Pessoa.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .build();
        DocumentoSaude documento = DocumentoSaude.builder()
                .estado(estado)
                .sigla(sigla)
                .numero(numero)
                .build();
        ProfissionalSaude profissionalSaude = ProfissionalSaude.builder()
                .documento(documento)
                .pessoa(pessoaMedico)
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

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> findById(@PathVariable Long id){
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
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
    // TODO: fazer um retorno para dar um médico por meio paciente.
    //             @RequestParam(name = "paciente.nome", required = false) String nmPaciente,
    //            @RequestParam(name = "paciente.cpf", required = false) String cpfPaciente
}
