package io.github.geovanealberto.usuarios.rest;


import io.github.geovanealberto.usuarios.model.entity.TipoQuestao;
import io.github.geovanealberto.usuarios.model.entity.TipoUsuario;
import io.github.geovanealberto.usuarios.model.entity.Usuario;
import io.github.geovanealberto.usuarios.model.repository.TipoUsuarioRepository;
import io.github.geovanealberto.usuarios.model.repository.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/tipo-usuario")
//@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Data
public class TipoUsuarioController {

    private final TipoUsuarioRepository repository;

    @GetMapping
    public List<TipoUsuario> obterTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoUsuario salvar(@RequestBody @Valid TipoUsuario tipoUsuario) {
        return repository.save(tipoUsuario);
    }

    @GetMapping("{id}")
    public TipoUsuario acharPorId(@PathVariable @Valid Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Usuario nao encontrado"));
    }
}
