package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.PessoaRequest;
import br.com.fiap.healy.dto.response.PessoaResponse;
import br.com.fiap.healy.entity.Pessoa;
import br.com.fiap.healy.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PessoaService implements ServiceDTO<Pessoa, PessoaRequest, PessoaResponse> {

    @Autowired
    private PessoaRepository repo;

    @Override
    public Pessoa toEntity(PessoaRequest pessoaRequest) {
        return Pessoa.builder()
                .nome(pessoaRequest.nome())
                .email(pessoaRequest.email())
                .nascimento(pessoaRequest.nascimento())
                .telefone(pessoaRequest.telefone())
                .build();
    }

    @Override
    public PessoaResponse toResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .email(pessoa.getEmail())
                .nascimento(pessoa.getNascimento())
                .telefone(pessoa.getTelefone())
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
    public Pessoa save(Pessoa pessoa) {
        return repo.save(pessoa);
    }
}
