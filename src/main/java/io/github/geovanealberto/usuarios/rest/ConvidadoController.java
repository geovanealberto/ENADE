package io.github.geovanealberto.usuarios.rest;


import io.github.geovanealberto.usuarios.exception.ConvidadoCadastradoException;
import io.github.geovanealberto.usuarios.model.entity.Convidado;
import io.github.geovanealberto.usuarios.service.ConvidadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/convidado")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar (@RequestBody @Valid Convidado convidado){
        try{
            service.salvar(convidado);
        }catch (ConvidadoCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
