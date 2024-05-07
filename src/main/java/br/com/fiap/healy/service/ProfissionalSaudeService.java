package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.ProfissionalSaudeRequest;
import br.com.fiap.healy.dto.response.PacienteResponse;
import br.com.fiap.healy.dto.response.ProfissionalSaudeResponse;
import br.com.fiap.healy.entity.Paciente;
import br.com.fiap.healy.entity.ProfissionalSaude;
import br.com.fiap.healy.repository.ProfissionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class ProfissionalSaudeService implements ServiceDTO<ProfissionalSaude, ProfissionalSaudeRequest, ProfissionalSaudeResponse>{
    @Autowired
    private ProfissionalSaudeRepository repo;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PacienteService pacienteService;
    @Override
    public ProfissionalSaude toEntity(ProfissionalSaudeRequest profissionalSaudeRequest) {
        if (Objects.isNull(profissionalSaudeRequest.pessoa().id())) return null;
        var pessoa = pessoaService.findById(profissionalSaudeRequest.pessoa().id());
        if (Objects.isNull(pessoa)) return null;

        Set<Paciente> pacientes = new LinkedHashSet<>();

        profissionalSaudeRequest.pacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.id())) {
                var p = pacienteService.findById(paciente.id());
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });

        return ProfissionalSaude.builder()
                .userMedico(profissionalSaudeRequest.userMedico())
                .senhaMedico(profissionalSaudeRequest.senhaMedico())
                .crm(profissionalSaudeRequest.crm())
                .pessoa(pessoa)
                .pacientes(pacientes)
                .build();
    }

    @Override
    public ProfissionalSaudeResponse toResponse(ProfissionalSaude profissionalSaude) {
        if (Objects.isNull(profissionalSaude)) return null;
        var pessoa = pessoaService.toResponse( profissionalSaude.getPessoa() );

        Set<PacienteResponse> pacientes = new LinkedHashSet<>();

        profissionalSaude.getPacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.getId())) {
                var p = pacienteService.toResponse(pacienteService.findById(paciente.getId()));
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });
        return ProfissionalSaudeResponse.builder()
                .id(profissionalSaude.getId())
                .userMedico(profissionalSaude.getUserMedico())
                .senhaMedico(profissionalSaude.getSenhaMedico())
                .crm(profissionalSaude.getCrm())
                .pessoa(pessoa)
                .pacientes(pacientes)
                .build();
    }

    @Override
    public Collection<ProfissionalSaude> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<ProfissionalSaude> findAll(Example<ProfissionalSaude> example) {
        return repo.findAll(example);
    }

    @Override
    public ProfissionalSaude findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public ProfissionalSaude save(ProfissionalSaude profissionalSaude) {
        return repo.save(profissionalSaude);
    }
}
