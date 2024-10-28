package br.com.fiap.healy.domain.repository;

import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude,Long> {
    List<ProfissionalSaude> findAllByPacientes(Pessoa pacientes);
}
