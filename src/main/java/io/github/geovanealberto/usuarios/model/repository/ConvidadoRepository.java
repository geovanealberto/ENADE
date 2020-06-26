package io.github.geovanealberto.usuarios.model.repository;

import io.github.geovanealberto.usuarios.model.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConvidadoRepository extends JpaRepository<Convidado, Integer> {
    Optional<Convidado> findByUsername(String username);

    boolean existsByUsername(String username);

}

