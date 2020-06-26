package io.github.geovanealberto.usuarios.rest;

import io.github.geovanealberto.usuarios.converter.ResultadoConverter;
import io.github.geovanealberto.usuarios.dto.ResultadoDTO;
import io.github.geovanealberto.usuarios.model.entity.Grafico;
import io.github.geovanealberto.usuarios.model.entity.Resultado;
import io.github.geovanealberto.usuarios.model.repository.ResultadoRepository;
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
@RequestMapping ("/api/resultado")

public class ResultadoController {

    private final ResultadoRepository repository;

    @Autowired
    public ResultadoController(ResultadoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @GetMapping
    public List<Resultado> obterTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultadoDTO salvar (@RequestBody @Valid ResultadoDTO resultadoDTO) {
        Resultado resultado = ResultadoConverter.converter(resultadoDTO);
        repository.save(resultado);
        return resultadoDTO;
    }

    @GetMapping("{id}")
    public ResultadoDTO acharPorId( @PathVariable @Valid Integer id){
        Resultado resultado = repository
                .findById(id)
                .orElseThrow( ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado nao encontrado"));
        ResultadoDTO resultadoDTO = ResultadoConverter.converter(resultado);
        return resultadoDTO;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar (@PathVariable Integer id){

        repository
                .findById(id)
                .map( resultado -> {
                    repository.delete(resultado);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado nao encontrado"));
    }

    @GetMapping(value ="/relatorio", produces = "application/text")
    public ResponseEntity<String> downloadRelatorio(HttpServletRequest request) throws Exception{
        byte[] pdf = relatorioService.gerarRelatorio("relatorio_resultado",
                request.getServletContext());

        String base64Pdf = "data:application/pdf;base64," + Base64.encodeBase64String(pdf);

        return new ResponseEntity<String>(base64Pdf, HttpStatus.OK);

    }

    @GetMapping(value = "/grafico", produces = "application/json")
    public ResponseEntity<Grafico> grafico(){

        Grafico grafico = new Grafico();

        List<String> resultadoGrafico = jdbcTemplate.queryForList("SELECT u.nomeUsuario tbusuario, r.valorObtido AS tbresultado\n" +
                "FROM tbusuario AS u\n" +
                "INNER JOIN tbresultado AS r ON u.idUsuario = r.tbUsuario_idUsuario", String.class);

        if (!resultadoGrafico.isEmpty()){
            String nome = resultadoGrafico.get(0).replaceAll("\\{", "").replaceAll("\\}", "");
            String nota = resultadoGrafico.get(1).replaceAll("\\{", "").replaceAll("\\}", "");

            grafico.setNome(nome);
            grafico.setNota(nota);

        }
        return new ResponseEntity<Grafico>(grafico, HttpStatus.OK);

    }





}
