package br.com.fiap.healy.repository;

import br.com.fiap.healy.entity.AreaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMedicaRepository extends JpaRepository<AreaMedica, Long> {
}
