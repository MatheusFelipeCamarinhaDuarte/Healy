package br.com.fiap.healy.resource;

import br.com.fiap.healy.dto.request.DocumentoSaudeRequest;
import br.com.fiap.healy.dto.response.DocumentoSaudeResponse;
import br.com.fiap.healy.entity.DocumentoSaude;
import br.com.fiap.healy.service.DocumentoSaudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.Doc;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/documentos-de-saude")
public class DocumentoSaudeResource implements ResourceDTO<DocumentoSaudeRequest, DocumentoSaudeResponse> {
    @Autowired
    private DocumentoSaudeService service;

    @GetMapping
    public ResponseEntity<Collection<DocumentoSaudeResponse>> findAll(
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "numero", required = false) String numero,
            @RequestParam(name = "sigla", required = false) String sigla
    ){
        DocumentoSaude documento = DocumentoSaude.builder()
                .sigla(sigla)
                .numero(numero)
                .estado(estado)
                .build();
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();
        Example<DocumentoSaude> example = Example.of(documento,matcher);
        var entities = service.findAll(example);
        var response = entities.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<DocumentoSaudeResponse> findById(Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DocumentoSaudeResponse> save(DocumentoSaudeRequest r) {
        var entity = service.toEntity(r);
        entity = service.save(entity);
        var response = service.toResponse(entity);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
