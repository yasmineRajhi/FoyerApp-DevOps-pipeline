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
@Table(name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReservation")
    private Long idReservation;
    private String numReservation;
    @Temporal (TemporalType.DATE)
    private Date anneeUniversitaire;
    private Boolean estValide;
    @ManyToMany(mappedBy="Reservations", cascade = CascadeType.ALL)
    private Set<Etudiant> Etudiants;
}
