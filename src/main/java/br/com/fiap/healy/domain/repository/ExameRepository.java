package br.com.fiap.healy.domain.repository;

import br.com.fiap.healy.domain.entity.Exame;
import br.com.fiap.healy.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findAllByPessoa(Pessoa pessoa);
    void deleteByPessoa(Pessoa pessoa);

}
