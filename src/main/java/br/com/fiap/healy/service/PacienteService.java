package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.PacienteRequest;
import br.com.fiap.healy.dto.response.PacienteResponse;
import br.com.fiap.healy.entity.HistoricoMedico;
import br.com.fiap.healy.entity.Paciente;
import br.com.fiap.healy.entity.Pessoa;
import br.com.fiap.healy.entity.PlanoSaude;
import br.com.fiap.healy.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class PacienteService implements ServiceDTO<Paciente, PacienteRequest, PacienteResponse> {
    @Autowired
    private PacienteRepository repo;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PlanoSaudeService planoSaudeService;
    @Autowired
    private HistoricoMedicoService historicoMedicoService;

    @Override
    public Paciente toEntity(PacienteRequest pacienteRequest) {
        if (Objects.isNull(pacienteRequest.pessoa().id())) return null;
        var pessoa = pessoaService.findById(pacienteRequest.pessoa().id());
        if (Objects.isNull(pessoa)) return null;

        PlanoSaude planoSaude = null;
        if (Objects.nonNull(pacienteRequest.pessoa().id())) {
            planoSaude = planoSaudeService.findById(pacienteRequest.plano().id());
        }

        HistoricoMedico historicoMedico = null;
        if (Objects.nonNull(pacienteRequest.pessoa().id())) {
            historicoMedico = historicoMedicoService.findById(pacienteRequest.historico().id());
        }

        return Paciente.builder()
                .userPaciente(pacienteRequest.userPaciente())
                .senhaPaciente(pacienteRequest.senhaPaciente())
                .cpf(pacienteRequest.cpf())
                .pessoa(pessoa)
                .plano(planoSaude)
                .historico(historicoMedico)
                .build();
    }

    @Override
    public PacienteResponse toResponse(Paciente paciente) {
        return null;
    }

    @Override
    public Collection<Paciente> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Paciente> findAll(Example<Paciente> example) {
        return repo.findAll(example);
    }

    @Override
    public Paciente findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return repo.save(paciente);
    }
}
