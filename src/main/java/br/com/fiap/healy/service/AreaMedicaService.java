package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.AreaMedicaRequest;
import br.com.fiap.healy.dto.response.AreaMedicaResponse;
import br.com.fiap.healy.entity.AreaMedica;
import br.com.fiap.healy.repository.AreaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AreaMedicaService implements ServiceDTO<AreaMedica, AreaMedicaRequest, AreaMedicaResponse> {
    @Autowired
    private AreaMedicaRepository repo;

    @Override
    public AreaMedica toEntity(AreaMedicaRequest areaMedicaRequest) {
        return AreaMedica.builder()
                .nome(areaMedicaRequest.nome())
                .descricao(areaMedicaRequest.descricao())
                .build();
    }

    @Override
    public AreaMedicaResponse toResponse(AreaMedica areaMedica) {
        return AreaMedicaResponse.builder()
                .id(areaMedica.getId())
                .nome(areaMedica.getNome())
                .descricao(areaMedica.getDescricao())
                .build();
    }

    @Override
    public Collection<AreaMedica> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<AreaMedica> findAll(Example<AreaMedica> example) {
        return repo.findAll(example);
    }

    @Override
    public AreaMedica findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public AreaMedica save(AreaMedica areaMedica) {
        return repo.save(areaMedica);
    }
}
