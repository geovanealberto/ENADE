package io.github.geovanealberto.usuarios.rest;

import io.github.geovanealberto.usuarios.converter.QuestaoConverter;
import io.github.geovanealberto.usuarios.converter.UsuarioConverter;
import io.github.geovanealberto.usuarios.dto.QuestaoDTO;
import io.github.geovanealberto.usuarios.dto.UsuarioDTO;
import io.github.geovanealberto.usuarios.model.entity.Questao;
import io.github.geovanealberto.usuarios.model.entity.TipoQuestao;
import io.github.geovanealberto.usuarios.model.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/questao")
//@CrossOrigin("http://localhost:4200")
public class QuestaoController {

    private final QuestaoRepository repository;

    @Autowired
    public QuestaoController(QuestaoRepository repository) { this.repository = repository;}

    @GetMapping
    public List<Questao> obterTodos(){ return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestaoDTO salvar (@RequestBody @Valid QuestaoDTO questaoDTO) {
        Questao questao = QuestaoConverter.converter(questaoDTO);
       repository.save(questao);
        return questaoDTO;
    }

    @GetMapping("{id}")
    public QuestaoDTO acharPorId( @PathVariable @Valid Integer id){
        Questao questao = repository
                .findById(id)
                .orElseThrow( ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Questao nao encontrada"));
        QuestaoDTO questaoDTO = QuestaoConverter.converter(questao);
        return questaoDTO;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid QuestaoDTO questaoDTO){

        repository
                .findById(id)
                .map( questao -> {
                    questao.setDescricaoQuestao(questaoDTO.getDescricaoQuestao());
                    questao.setAlternativaA(questaoDTO.getAlternativaA());
                    questao.setAlternativaB(questaoDTO.getAlternativaB());
                    questao.setAlternativaC(questaoDTO.getAlternativaC());
                    questao.setAlternativaD(questaoDTO.getAlternativaD());
                    questao.setAlternativaE(questaoDTO.getAlternativaE());
                    questao.setAlternativaCorreta(questaoDTO.getAlternativaCorreta());
                    questao.setEstadoQuestao(questaoDTO.getEstadoQuestao());
                   // questao.getTipoQuestaoidTipoQuestao().setIdTipoQuestao(questaoDTO.getIdTipoQuestao());
                    return repository.save(questao);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questao nao encontrada"));
    }
}

