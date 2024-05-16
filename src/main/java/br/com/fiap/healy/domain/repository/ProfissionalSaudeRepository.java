package br.com.fiap.healy.domain.repository;

import br.com.fiap.healy.domain.entity.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude,Long> {
}
