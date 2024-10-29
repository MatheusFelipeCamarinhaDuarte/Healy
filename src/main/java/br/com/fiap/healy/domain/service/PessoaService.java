package br.com.fiap.healy.domain.service;

import br.com.fiap.healy.domain.dto.request.PessoaRequest;
import br.com.fiap.healy.domain.dto.response.PessoaResponse;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PessoaService implements ServiceDTO<Pessoa, PessoaRequest, PessoaResponse> {

    @Autowired
    private PessoaRepository repo;

    @Override
    public Pessoa toEntity(PessoaRequest dto) {
        return Pessoa.builder()
                .nome(dto.nome())
                .email(dto.email())
                .cpf(dto.cpf())
                .nascimento(dto.nascimento())
                .build();
    }

    @Override
    public PessoaResponse toResponse(Pessoa e) {
        return PessoaResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .email(e.getEmail())
                .nascimento(e.getNascimento())
                .build();
    }

    @Override
    public Collection<Pessoa> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Pessoa> findAll(Example<Pessoa> example) {
        return repo.findAll(example);
    }

    @Override
    public Pessoa findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Pessoa save(Pessoa e) {
        return repo.save(e);
    }
}
