package io.github.geovanealberto.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbTipoQuestao")
public class TipoQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoQuestao")
    private Integer idTipoQuestao;

    @Column(nullable = false, length = 45 , name="nomeTipoQuestao")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeTipoQuestao;

   @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbTipoQuestaoidTipoQuestao")
    private List<Questao> tbQuestaoList;
}
