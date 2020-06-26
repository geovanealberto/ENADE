package io.github.geovanealberto.usuarios.converter;

import io.github.geovanealberto.usuarios.dto.ProvaDTO;
import io.github.geovanealberto.usuarios.model.entity.Prova;



public class ProvaConverter {

    public static ProvaDTO converter(Prova prova) {
        ProvaDTO provaDTO = new ProvaDTO();
       provaDTO.setIdProva(prova.getIdProva());
       provaDTO.setDataProva(prova.getDataProva());
        return provaDTO;
    }

    public static Prova converter(ProvaDTO provaDTO) {
       Prova prova = new Prova();
        prova.setIdProva(provaDTO.getIdProva());
        prova.setDataProva(provaDTO.getDataProva());
        return prova;
    }

}