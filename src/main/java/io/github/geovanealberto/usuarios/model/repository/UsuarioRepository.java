package io.github.geovanealberto.usuarios.model.repository;


import io.github.geovanealberto.usuarios.dto.GraficoDTO;
import io.github.geovanealberto.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(" SELECT new io.github.geovanealberto.usuarios.dto.GraficoDTO(u.nomeUsuario, rst.valorObtido) from Usuario u inner join u.tbResultadoList rst where rst.tbUsuarioidUsuario = u.idUsuario")
    public List<GraficoDTO> listaGraficos();
}
