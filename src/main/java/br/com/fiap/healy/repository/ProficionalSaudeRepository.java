package br.com.fiap.healy.repository;

import br.com.fiap.healy.entity.ProficionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProficionalSaudeRepository extends JpaRepository<ProficionalSaude,Long> {
}
