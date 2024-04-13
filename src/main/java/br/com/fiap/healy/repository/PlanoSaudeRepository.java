package br.com.fiap.healy.repository;

import br.com.fiap.healy.entity.PlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoSaudeRepository extends JpaRepository<PlanoSaude, Long> {
}
