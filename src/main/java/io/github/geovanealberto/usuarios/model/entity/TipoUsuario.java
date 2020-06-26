package io.github.geovanealberto.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbTipoUsuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoUsuario;

    @Column(nullable = false, length = 20)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeTipoUsuario;

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tbTipoUsuarioidTipoUsuario")
    private List<Usuario> tbUsuariosList;


}
