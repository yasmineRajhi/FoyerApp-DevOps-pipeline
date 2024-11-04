package tn.esprit.tpfoyer.entity;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;
    @ManyToOne
    Foyer foy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="bl")
    private Set<Chambre> chambreSet;
}
