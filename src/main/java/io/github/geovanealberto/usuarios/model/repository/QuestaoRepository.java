package io.github.geovanealberto.usuarios.model.repository;

import io.github.geovanealberto.usuarios.model.entity.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

}
