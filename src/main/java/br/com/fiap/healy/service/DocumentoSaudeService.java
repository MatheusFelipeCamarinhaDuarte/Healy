package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.DocumentoSaudeRequest;
import br.com.fiap.healy.dto.response.DocumentoSaudeResponse;
import br.com.fiap.healy.entity.DocumentoSaude;
import br.com.fiap.healy.repository.DocumentoSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DocumentoSaudeService implements ServiceDTO<DocumentoSaude, DocumentoSaudeRequest, DocumentoSaudeResponse> {
    @Autowired
    private DocumentoSaudeRepository repo;

    @Override
    public DocumentoSaude toEntity(DocumentoSaudeRequest dto) {
        return DocumentoSaude.builder()
                .estado(dto.estado())
                .sigla(dto.sigla())
                .numero(dto.numero())
                .build();
    }

    @Override
    public DocumentoSaudeResponse toResponse(DocumentoSaude e) {
        return DocumentoSaudeResponse.builder()
                .estado(e.getEstado())
                .sigla(e.getSigla())
                .numero(e.getNumero())
                .build();
    }

    @Override
    public Collection<DocumentoSaude> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<DocumentoSaude> findAll(Example<DocumentoSaude> example) {
        return repo.findAll(example);

    }

    @Override
    public DocumentoSaude findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public DocumentoSaude save(DocumentoSaude e) {
        return repo.save(e);
    }
}
