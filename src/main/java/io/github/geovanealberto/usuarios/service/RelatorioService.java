package io.github.geovanealberto.usuarios.service;

import io.github.geovanealberto.usuarios.dto.GraficoDTO;
import io.github.geovanealberto.usuarios.model.repository.UsuarioRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@Service
public class RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public byte[] gerarRelatorio (String nomeRelatorio, ServletContext servletContext) throws Exception {


        /*Conexao com o banco*/
        Connection connection = jdbcTemplate.getDataSource().getConnection();

        /*Caminho do arquivo jasper*/
        String caminhoJasper = "C:/Users/geova/projeto/Trabalho Final/IdeaProjects/enade/src/main/resources/relatorios/" + nomeRelatorio  + ".jasper";

        /*Gerar o  relatorio com os dados e conexao */
        JasperPrint print = JasperFillManager
                .fillReport(caminhoJasper,
                        new HashMap(),
                        connection);

        byte[] retorno = JasperExportManager.exportReportToPdf(print);
        connection.close();

        return retorno;

    }



    /*public List<Usuario> listUsuario(){
        return usuarioRepository.teste();
    }*/

    public List<GraficoDTO> listGraficoDTO(){
        return usuarioRepository.listaGraficos();
    }
}
