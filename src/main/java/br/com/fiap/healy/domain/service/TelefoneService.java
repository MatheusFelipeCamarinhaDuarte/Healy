package br.com.fiap.healy.domain.service;

import br.com.fiap.healy.domain.dto.request.TelefoneRequest;
import br.com.fiap.healy.domain.dto.response.TelefoneResponse;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Telefone;
import br.com.fiap.healy.domain.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class TelefoneService implements ServiceDTO<Telefone, TelefoneRequest, TelefoneResponse> {
    @Autowired
    private TelefoneRepository repo;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public Telefone toEntity(TelefoneRequest dto) {
        Pessoa pessoa = null;
        if(Objects.nonNull(dto.pessoa())){
            pessoa = pessoaService.findById(dto.pessoa().id());
        }
        return Telefone.builder()
                .ddi(dto.ddi())
                .ddd(dto.ddd())
                .numero(dto.numero())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public TelefoneResponse toResponse(Telefone e) {
        var pessoa = pessoaService.toResponse(e.getPessoa());

        return TelefoneResponse.builder()
                .id(e.getId())
                .ddi(e.getDdi())
                .ddd(e.getDdd())
                .numero(e.getNumero())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public Collection<Telefone> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Telefone> findAll(Example<Telefone> example) {
        return repo.findAll(example);
    }

    @Override
    public Telefone findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Telefone save(Telefone e) {
        return repo.save(e);
    }
}
