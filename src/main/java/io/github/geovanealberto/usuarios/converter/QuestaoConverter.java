package io.github.geovanealberto.usuarios.converter;


import io.github.geovanealberto.usuarios.dto.QuestaoDTO;
import io.github.geovanealberto.usuarios.model.entity.Questao;
import io.github.geovanealberto.usuarios.model.entity.TipoQuestao;

public class QuestaoConverter {

    public static QuestaoDTO converter(Questao questao) {
        QuestaoDTO questaoDTO = new QuestaoDTO();
        questaoDTO.setIdQuestao(questao.getIdQuestao());
        questaoDTO.setDescricaoQuestao(questao.getDescricaoQuestao());
        questaoDTO.setAlternativaA(questao.getAlternativaA());
        questaoDTO.setAlternativaB(questao.getAlternativaB());
        questaoDTO.setAlternativaC(questao.getAlternativaC());
        questaoDTO.setAlternativaD(questao.getAlternativaD());
        questaoDTO.setAlternativaE(questao.getAlternativaE());
        questaoDTO.setAlternativaCorreta(questao.getAlternativaCorreta());
        questaoDTO.setEstadoQuestao(questao.getEstadoQuestao());
        questaoDTO.setIdTipoQuestao(questao.getTbTipoQuestaoidTipoQuestao().getIdTipoQuestao());
        return questaoDTO;
    }

   public static Questao converter(QuestaoDTO questaoDTO) {
       Questao questao = new Questao();
       questao.setIdQuestao(questaoDTO.getIdQuestao());
       questao.setDescricaoQuestao(questaoDTO.getDescricaoQuestao());
       questao.setAlternativaA(questaoDTO.getAlternativaA());
       questao.setAlternativaB(questaoDTO.getAlternativaB());
       questao.setAlternativaC(questaoDTO.getAlternativaC());
       questao.setAlternativaD(questaoDTO.getAlternativaD());
       questao.setAlternativaE(questaoDTO.getAlternativaE());
       questao.setAlternativaCorreta(questaoDTO.getAlternativaCorreta());
       questao.setEstadoQuestao(questaoDTO.getEstadoQuestao());
       TipoQuestao tipoQuestao = new TipoQuestao();
       tipoQuestao.setIdTipoQuestao(questaoDTO.getIdTipoQuestao());
       questao.setTbTipoQuestaoidTipoQuestao(tipoQuestao);
       return questao;
    }
}
