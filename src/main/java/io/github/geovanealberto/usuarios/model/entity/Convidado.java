package io.github.geovanealberto.usuarios.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor
@Table(name="tbConvidado")
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column  (name="id")
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @Column  (name="senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;

}
