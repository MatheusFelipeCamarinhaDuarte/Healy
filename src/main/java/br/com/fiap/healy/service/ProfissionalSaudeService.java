package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.ProfissionalSaudeRequest;
import br.com.fiap.healy.dto.response.PessoaResponse;
import br.com.fiap.healy.dto.response.ProfissionalSaudeResponse;
import br.com.fiap.healy.entity.Pessoa;
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
public class ProfissionalSaudeService implements ServiceDTO<ProfissionalSaude, ProfissionalSaudeRequest, ProfissionalSaudeResponse> {
    @Autowired
    private ProfissionalSaudeRepository repo;
    @Autowired
    private PessoaService pessoaService;

    @Override
    public ProfissionalSaude toEntity(ProfissionalSaudeRequest dto) {
        if (Objects.isNull(dto.pessoa().id())) return null;
        var pessoa = pessoaService.findById(dto.pessoa().id());
        if (Objects.isNull(pessoa)) return null;

        Set<Pessoa> pacientes = new LinkedHashSet<>();

        dto.pacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.id())) {
                var p = pessoaService.findById(paciente.id());
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });

        return ProfissionalSaude.builder()
                .pessoa(pessoa)
                .pacientes(pacientes)
                .build();
    }

    @Override
    public ProfissionalSaudeResponse toResponse(ProfissionalSaude e) {
        if (Objects.isNull(e)) return null;
        var pessoa = pessoaService.toResponse(e.getPessoa());

        Set<PessoaResponse> pacientes = new LinkedHashSet<>();

        e.getPacientes().forEach(paciente -> {
            if (Objects.nonNull(paciente.getId())) {
                var p = pessoaService.toResponse(pessoaService.findById(paciente.getId()));
                if (p != null) {
                    pacientes.add(p);
                }
            }
        });
        return ProfissionalSaudeResponse.builder()
                .id(e.getId())
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
    public ProfissionalSaude save(ProfissionalSaude e) {
        return repo.save(e);
    }
}
