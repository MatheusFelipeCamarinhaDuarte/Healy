package br.com.fiap.healy.domain.repository;

import br.com.fiap.healy.domain.entity.Exame;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findAllByNome(String Nome);
}
