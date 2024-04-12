package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.HistoricoMedicoRequest;
import br.com.fiap.healy.dto.response.HistoricoMedicoResponse;
import br.com.fiap.healy.entity.HistoricoMedico;
import br.com.fiap.healy.repository.HistoricoMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HistoricoMedicoService implements ServiceDTO<HistoricoMedico, HistoricoMedicoRequest, HistoricoMedicoResponse>{
    @Autowired
    private HistoricoMedicoRepository repo;
    @Override
    public HistoricoMedico toEntity(HistoricoMedicoRequest historicoMedicoRequest) {
        return HistoricoMedico.builder()
                .doencas(historicoMedicoRequest.doencas())
                .doencasAnteriores(historicoMedicoRequest.doencasAnteriores())
                .alergias(historicoMedicoRequest.alergias())
                .medicamento(historicoMedicoRequest.medicamento())
                .build();
    }

    @Override
    public HistoricoMedicoResponse toResponse(HistoricoMedico historicoMedico) {
        return HistoricoMedicoResponse.builder()
                .doencas(historicoMedico.getDoencas())
                .doencasAnteriores(historicoMedico.getDoencasAnteriores())
                .alergias(historicoMedico.getAlergias())
                .medicamento(historicoMedico.getMedicamento())
                .build();
    }

    @Override
    public Collection<HistoricoMedico> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<HistoricoMedico> findAll(Example<HistoricoMedico> example) {
        return repo.findAll(example);
    }

    @Override
    public HistoricoMedico findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public HistoricoMedico save(HistoricoMedico historicoMedico) {
        return repo.save(historicoMedico);
    }
}
