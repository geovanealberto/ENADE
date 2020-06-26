package io.github.geovanealberto.usuarios.converter;

import io.github.geovanealberto.usuarios.dto.UsuarioDTO;
import io.github.geovanealberto.usuarios.model.entity.TipoUsuario;
import io.github.geovanealberto.usuarios.model.entity.Usuario;

public class UsuarioConverter {

    public static UsuarioDTO converter(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNomeUsuario(usuario.getNomeUsuario());
        usuarioDTO.setEmailUsuario(usuario.getEmailUsuario());
        usuarioDTO.setSenhaUsuario(usuario.getSenhaUsuario());
        usuarioDTO.setIdTipoUsuario(usuario.getTbTipoUsuarioidTipoUsuario().getIdTipoUsuario());
        return usuarioDTO;
    }

    public static Usuario converter(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
        usuario.setEmailUsuario(usuarioDTO.getEmailUsuario());
        usuario.setSenhaUsuario(usuarioDTO.getSenhaUsuario());
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setIdTipoUsuario(usuarioDTO.getIdTipoUsuario());
        usuario.setTbTipoUsuarioidTipoUsuario(tipoUsuario);
        return usuario;
    }
}
