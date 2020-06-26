package io.github.geovanealberto.usuarios.model.repository;

import io.github.geovanealberto.usuarios.model.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}
