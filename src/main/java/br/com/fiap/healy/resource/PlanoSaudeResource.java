package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.PlanoSaudeRequest;
import br.com.fiap.healy.dto.response.PlanoSaudeResponse;
import br.com.fiap.healy.entity.AreaMedica;
import br.com.fiap.healy.entity.PlanoSaude;
import br.com.fiap.healy.service.AreaMedicaService;
import br.com.fiap.healy.service.PlanoSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private AreaMedicaService areaMedicaService;

    @GetMapping
    public List<PlanoSaudeResponse> findAll() {
        return service.findAll().stream().map(service::toResponse).toList();
    }

    @GetMapping(value = "/{id}")
    public PlanoSaudeResponse findById(@PathVariable Long id) {
        return service.toResponse(service.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PlanoSaudeResponse> save(@RequestBody PlanoSaudeRequest planoSaudeRequest) {

        var entity = service.toEntity(planoSaudeRequest);

        Set<AreaMedica> areas  = new LinkedHashSet<>();
        planoSaudeRequest.area().forEach( area ->{
            var a = areaMedicaService.findById(area.id());
            areas.add(a);
        });
        if (Objects.nonNull( planoSaudeRequest )) entity.setAreaMedica(areas);


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
