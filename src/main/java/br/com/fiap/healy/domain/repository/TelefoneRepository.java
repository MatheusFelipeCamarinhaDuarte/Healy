package br.com.fiap.healy.domain.repository;

import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
