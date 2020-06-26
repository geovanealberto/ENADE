package io.github.geovanealberto.usuarios.dto;


import lombok.Data;

@Data
public class UsuarioDTO {


	private Integer idUsuario;

	private String nomeUsuario;

	private String emailUsuario;

	private String senhaUsuario;

	private Integer idTipoUsuario;

}
