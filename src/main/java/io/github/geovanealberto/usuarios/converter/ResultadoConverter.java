package io.github.geovanealberto.usuarios.converter;

import io.github.geovanealberto.usuarios.dto.ResultadoDTO;
import io.github.geovanealberto.usuarios.model.entity.Prova;
import io.github.geovanealberto.usuarios.model.entity.Resultado;
import io.github.geovanealberto.usuarios.model.entity.Usuario;


public class ResultadoConverter {

    public static ResultadoDTO converter(Resultado resultado) {
       ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setIdResultado(resultado.getIdResultado());
        resultadoDTO.setValorObtido(resultado.getValorObtido());
        resultadoDTO.setIdProva(resultado.getTbProvaidProva().getIdProva());
        resultadoDTO.setIdUsuario(resultado.getTbUsuarioidUsuario().getIdUsuario());
        return resultadoDTO;
    }

    public static Resultado converter(ResultadoDTO resultadoDTO) {
        Resultado resultado = new Resultado();
        resultado.setIdResultado(resultadoDTO.getIdResultado());
        resultado.setValorObtido(resultadoDTO.getValorObtido());

        Prova prova = new Prova();
        Usuario usuario = new Usuario();

        prova.setIdProva(resultadoDTO.getIdProva());
        resultado.setTbProvaidProva(prova);

        usuario.setIdUsuario(resultadoDTO.getIdUsuario());
        resultado.setTbUsuarioidUsuario(usuario);
        return resultado;
    }
}
