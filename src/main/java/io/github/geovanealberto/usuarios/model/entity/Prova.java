package io.github.geovanealberto.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbProva")
public class Prova implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProva")
    private Integer idProva;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dataProva")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataProva;

    @JsonIgnore
    @JoinTable(name = "tbProva_has_tbQuestao", joinColumns = {
            @JoinColumn(name = "tbProva_idProva", referencedColumnName = "idProva")}, inverseJoinColumns = {
            @JoinColumn(name = "tbQuestao_idQuestao", referencedColumnName = "idQuestao")})
    @ManyToMany
    private List<Questao> tbQuestaoList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbProvaidProva")
    private List<Resultado> tbResultadoList;



}
