package io.github.geovanealberto.usuarios.dto;

import lombok.Data;

@Data
public class QuestaoDTO {

    private Integer idQuestao;

    private String descricaoQuestao;

    private String alternativaA;

    private String alternativaB;

    private String alternativaC;

    private String alternativaD;

    private String alternativaE;

    private String alternativaCorreta;

    private Integer estadoQuestao;

    private Integer idTipoQuestao;
}
