package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.PlanoSaudeRequest;
import br.com.fiap.healy.dto.response.AreaMedicaResponse;
import br.com.fiap.healy.dto.response.PlanoSaudeResponse;
import br.com.fiap.healy.repository.PlanoSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class PlanoSaudeService implements ServiceDTO<PlanoSaude, PlanoSaudeRequest, PlanoSaudeResponse> {

    @Autowired
    private PlanoSaudeRepository repo;

    @Autowired
    private AreaMedicaService areaMedicaService;

    @Override
    public PlanoSaude toEntity(PlanoSaudeRequest planoSaudeRequest) {
        Set<AreaMedica> areas = new LinkedHashSet<>();

        planoSaudeRequest.area().forEach(area -> {
            if (Objects.nonNull(area.id())) {
                var a = areaMedicaService.findById(area.id());
                if (a != null) {
                    areas.add(a);
                }
            }
        });
        if (Objects.isNull(planoSaudeRequest.valor())) return null;


        return PlanoSaude.builder()
                .areaMedica(areas)
                .valor(planoSaudeRequest.valor())
                .build();
    }

    @Override
    public PlanoSaudeResponse toResponse(PlanoSaude planoSaude) {
        Set<AreaMedicaResponse> areas = new LinkedHashSet<>();

        planoSaude.getAreaMedica().forEach(areaMedica -> {
            if (Objects.nonNull(areaMedica.getId())) {
                var a = areaMedicaService.toResponse(areaMedicaService.findById(areaMedica.getId()));
                if (a != null) {
                    areas.add(a);
                }
            }
        });

        return PlanoSaudeResponse.builder()
                .id(planoSaude.getId())
                .area(areas)
                .valor(planoSaude.getValor())
                .build();

    }

    @Override
    public Collection<PlanoSaude> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<PlanoSaude> findAll(Example<PlanoSaude> example) {
        return repo.findAll(example);
    }

    @Override
    public PlanoSaude findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public PlanoSaude save(PlanoSaude planoSaude) {
        return repo.save(planoSaude);
    }
}
