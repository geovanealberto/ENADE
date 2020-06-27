package io.github.geovanealberto.usuarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/api/clientes/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/api/convidado").permitAll()
                //.antMatchers("/api/relatorio").permitAll()
                //.antMatchers("/api/resultado/relatorio").permitAll()

                .antMatchers("/api/usuarios/**").authenticated()
                .antMatchers("/api/tipo_usuario/**").authenticated()

                .antMatchers("/api/questao/**").authenticated()
                .antMatchers("/api/tipo_questao/**").authenticated()

                .antMatchers("/api/resultado/**").permitAll()
                .antMatchers("/api/prova/**").authenticated()

                .anyRequest().denyAll();
        ;
    }
}