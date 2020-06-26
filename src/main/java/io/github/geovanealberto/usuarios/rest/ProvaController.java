package io.github.geovanealberto.usuarios.rest;

import io.github.geovanealberto.usuarios.converter.ProvaConverter;
import io.github.geovanealberto.usuarios.dto.ProvaDTO;
import io.github.geovanealberto.usuarios.model.entity.Prova;
import io.github.geovanealberto.usuarios.model.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/prova")

public class ProvaController {

        private final ProvaRepository repository;

        @Autowired
        public ProvaController(ProvaRepository repository) {
            this.repository = repository;
        }

        @GetMapping
        public List<Prova> obterTodos() {
            return repository.findAll();
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ProvaDTO salvar (@RequestBody @Valid ProvaDTO provaDTO) {
            Prova prova = ProvaConverter.converter(provaDTO);
            repository.save(prova);
            return provaDTO;
        }

        @GetMapping("{id}")
        public ProvaDTO acharPorId( @PathVariable @Valid Integer id){
            Prova prova = repository
                    .findById(id)
                    .orElseThrow( ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Prova nao encontrado"));
            ProvaDTO provaDTO = ProvaConverter.converter(prova);
            return provaDTO;
        }

        @DeleteMapping("{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletar (@PathVariable Integer id){

            repository
                    .findById(id)
                    .map( prova -> {
                        repository.delete(prova);
                        return Void.TYPE;
                    })
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prova nao encontrado"));
        }

    }
