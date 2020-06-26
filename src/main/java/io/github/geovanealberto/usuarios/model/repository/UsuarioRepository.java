package io.github.geovanealberto.usuarios.model.repository;


import io.github.geovanealberto.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
