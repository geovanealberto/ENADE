package io.github.geovanealberto.usuarios.service;

import io.github.geovanealberto.usuarios.exception.ConvidadoCadastradoException;
import io.github.geovanealberto.usuarios.model.entity.Convidado;
import io.github.geovanealberto.usuarios.model.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ConvidadoService implements UserDetailsService {

    @Autowired
    private ConvidadoRepository repository;

    public Convidado salvar (Convidado convidado){
        boolean exists = repository.existsByUsername(convidado.getUsername());
        if(exists){
            throw new ConvidadoCadastradoException(convidado.getUsername());
        }

        return repository.save(convidado);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Convidado convidado = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Convidado nao encontrado."));

        return User
                .builder()
                .username(convidado.getUsername())
                .password(convidado.getPassword())
                .roles("USER")
                .build()
                ;
    }
}
