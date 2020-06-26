package io.github.geovanealberto.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbQuestao")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestao")
    private Integer idQuestao;

    @Column(name = "descricaoQuestao", nullable = false, length = 45)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricaoQuestao;

    @Column(name = "alternativaA", nullable = true, length = 45)
    //@NotEmpty(message = "{campo.alternativaA.obrigatorio}")
    private String alternativaA;

    @Column  (name = "alternativaB", nullable = true, length = 45)
    //@NotNull(message = "{campo.alternativaB.obrigatorio}")
    private String alternativaB;

    @Column  (name = "alternativaC", nullable = true, length = 45)
   // @NotNull(message = "{campo.alternativaC.obrigatorio}")
    private String alternativaC;

    @Column  (name = "alternativaD", nullable = true, length = 45)
   // @NotNull(message = "{campo.alternativaD.obrigatorio}")
    private String alternativaD;

    @Column  (name = "alternativaE", nullable = true, length = 45)
    //@NotNull(message = "{campo.alternativaE.obrigatorio}")
    private String alternativaE;

    @Column(name = "alternativaCorreta", nullable = true, length = 1)
    //@NotEmpty(message = "{campo.resposta.obrigatorio}")
    private String alternativaCorreta;

    @Column(name = "estadoQuestao", nullable = true)
    //@NotEmpty(message = "{campo.estado.obrigatorio}")
    private Integer estadoQuestao;

    @ManyToMany(mappedBy = "tbQuestaoList")
    private List<Prova> tbProvaList;

   @ManyToOne(optional = false)
    @JoinColumn(name = "tbTipoQuestao_idTipoQuestao", referencedColumnName = "idTipoQuestao")
    private TipoQuestao tbTipoQuestaoidTipoQuestao;
}

