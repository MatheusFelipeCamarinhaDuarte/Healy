package br.com.fiap.healy.repository;

import br.com.fiap.healy.entity.HistoricoMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedico, Long> {
}
