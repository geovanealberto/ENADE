package io.github.geovanealberto.usuarios.exception;

import javax.validation.constraints.NotEmpty;

public class ConvidadoCadastradoException extends RuntimeException {

    public ConvidadoCadastradoException(String login) {
        super ("Convidado ja cadastrado para o login" + login);
    }
}
