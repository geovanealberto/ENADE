package io.github.geovanealberto.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbUsuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name ="nomeUsuario",nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeUsuario;

    @Column(name = "emailUsuario",nullable = false, length = 150)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String emailUsuario;

    @Column  (name = "senhaUsuario",nullable = false, length = 20)
    @NotNull (message = "{campo.senha.obrigatorio}")
    private String senhaUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tbTipoUsuario_idTipoUsuario", referencedColumnName = "idTipoUsuario")
    private TipoUsuario tbTipoUsuarioidTipoUsuario;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbUsuarioidUsuario")
    private List<Resultado> tbResultadoList;

}
