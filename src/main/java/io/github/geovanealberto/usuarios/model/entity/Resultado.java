package io.github.geovanealberto.usuarios.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbResultado")
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResultado")
    private Integer idResultado;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valorObtido")
    private double valorObtido;

    @JoinColumn(name = "tbProva_idProva", referencedColumnName = "idProva")
    @ManyToOne(optional = false)
    private Prova tbProvaidProva;

    @JoinColumn(name = "tbUsuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario tbUsuarioidUsuario;

}


