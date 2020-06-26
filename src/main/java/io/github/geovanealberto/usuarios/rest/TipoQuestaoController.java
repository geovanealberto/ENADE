package io.github.geovanealberto.usuarios.rest;

import io.github.geovanealberto.usuarios.dto.QuestaoDTO;
import io.github.geovanealberto.usuarios.model.entity.TipoQuestao;
import io.github.geovanealberto.usuarios.model.repository.TipoQuestaoRepository;
import io.github.geovanealberto.usuarios.model.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-questao")
//@CrossOrigin("http://localhost:4200")
public class TipoQuestaoController {

    private final TipoQuestaoRepository repository;

    @Autowired
    public TipoQuestaoController(TipoQuestaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TipoQuestao> obterTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoQuestao salvar(@RequestBody @Valid TipoQuestao tipoQuestao) {
        return repository.save(tipoQuestao);
    }

    @GetMapping("{id}")
    public TipoQuestao acharPorId(@PathVariable @Valid Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de Questao nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid TipoQuestao tipoQuestaoAtualizado){

        repository
                .findById(id)
                .map( tipoQuestao -> {
                    tipoQuestao.setNomeTipoQuestao(tipoQuestaoAtualizado.getNomeTipoQuestao());
                    //tipoQuestaoAtualizado.setIdTipoQuestao(tipoQuestao.getIdTipoQuestao());
                    return repository.save(tipoQuestao);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo Cliente nao encontrado"));
    }
}
