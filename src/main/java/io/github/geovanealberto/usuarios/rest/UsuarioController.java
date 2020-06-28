package io.github.geovanealberto.usuarios.rest;


import io.github.geovanealberto.usuarios.converter.UsuarioConverter;
import io.github.geovanealberto.usuarios.dto.UsuarioDTO;
import io.github.geovanealberto.usuarios.model.entity.Usuario;
import io.github.geovanealberto.usuarios.model.repository.UsuarioRepository;
import io.github.geovanealberto.usuarios.service.RelatorioService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/usuarios")
//@CrossOrigin("http://localhost:4200")
public class UsuarioController {

    private final UsuarioRepository repository;

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> obterTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public UsuarioDTO salvar (@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioConverter.converter(usuarioDTO);
        repository.save(usuario);
        return usuarioDTO;
    }


    @GetMapping("{id}")
    public UsuarioDTO acharPorId( @PathVariable @Valid Integer id){
        Usuario usuario = repository
                .findById(id)
                .orElseThrow( ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
        UsuarioDTO usuarioDTO = UsuarioConverter.converter(usuario);
        return usuarioDTO;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Integer id){

        repository
                .findById(id)
                .map( usuario -> {
                   repository.delete(usuario);
                   return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid UsuarioDTO usuarioDTO){

        repository
                .findById(id)
                .map( usuario -> {
                    usuario.setNomeUsuario(usuarioDTO.getNomeUsuario());
                    usuario.setEmailUsuario(usuarioDTO.getEmailUsuario());
                    usuario.setSenhaUsuario(usuarioDTO.getSenhaUsuario());
                    //usuario.getTipoUsuarioidTipoUsuario().setIdTipoUsuario(usuarioDTO.getIdTipoUsuario());

                    return repository.save(usuario);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
    }


    @GetMapping(value ="/relatorio", produces = "application/text")
    public ResponseEntity<String> downloadRelatorioUsuario(HttpServletRequest request) throws Exception{
        byte[] pdf = relatorioService.gerarRelatorio("relatorio_usuarios",
                request.getServletContext());

        String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);

        return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);

    }
}
