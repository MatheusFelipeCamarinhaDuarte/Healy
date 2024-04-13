package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.ProficionalSaudeRequest;
import br.com.fiap.healy.dto.response.PacienteResponse;
import br.com.fiap.healy.dto.response.ProficionalSaudeResponse;
import br.com.fiap.healy.entity.Paciente;
import br.com.fiap.healy.entity.ProficionalSaude;
import br.com.fiap.healy.repository.ProficionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class ProficionalSaudeService implements ServiceDTO<ProficionalSaude, ProficionalSaudeRequest, ProficionalSaudeResponse>{
    @Autowired
    private ProficionalSaudeRepository repo;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PacienteService pacienteService;
    @Override
    public ProficionalSaude toEntity(ProficionalSaudeRequest proficionalSaudeRequest) {
        if (Objects.isNull(proficionalSaudeRequest.pessoa().id())) return null;
        var pessoa = pessoaService.findById(proficionalSaudeRequest.pessoa().id());
        if (Objects.isNull(pessoa)) return null;

        Set<Paciente> pacientes = new LinkedHashSet<>();

        proficionalSaudeRequest.pacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.id())) {
                var p = pacienteService.findById(paciente.id());
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });

        return ProficionalSaude.builder()
                .userMedico(proficionalSaudeRequest.userMedico())
                .senhaMedico(proficionalSaudeRequest.senhaMedico())
                .crm(proficionalSaudeRequest.crm())
                .pessoa(pessoa)
                .pacientes(pacientes)
                .build();
    }

    @Override
    public ProficionalSaudeResponse toResponse(ProficionalSaude proficionalSaude) {
        if (Objects.isNull(proficionalSaude)) return null;
        var pessoa = pessoaService.toResponse( proficionalSaude.getPessoa() );

        Set<PacienteResponse> pacientes = new LinkedHashSet<>();

        proficionalSaude.getPacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.getId())) {
                var p = pacienteService.toResponse(pacienteService.findById(paciente.getId()));
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });
        return ProficionalSaudeResponse.builder()
                .id(proficionalSaude.getId())
                .userMedico(proficionalSaude.getUserMedico())
                .senhaMedico(proficionalSaude.getSenhaMedico())
                .crm(proficionalSaude.getCrm())
                .pessoa(pessoa)
                .pacientes(pacientes)
                .build();
    }

    @Override
    public Collection<ProficionalSaude> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<ProficionalSaude> findAll(Example<ProficionalSaude> example) {
        return repo.findAll(example);
    }

    @Override
    public ProficionalSaude findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ProficionalSaude save(ProficionalSaude proficionalSaude) {
        return repo.save(proficionalSaude);
    }
}
