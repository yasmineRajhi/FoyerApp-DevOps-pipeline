package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;
    ChambreRepository chambreRepository;
    EtudiantRepository etudiantRepository;
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).get();
    }
    public Reservation addReservation(Reservation c) {
        return reservationRepository.save(c);
    }
    public void removeReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation ajouterReservation(Long idChambre, Long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).get();
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setNumReservation(chambre.getNumChambre()+"-"+chambre.getBl().getNomBloc()+"-"+ cinEtudiant);
        reservation.setEstValide(true);

        // Déterminer la capacité maximale en fonction du type de chambre
        int capMax = 0;
        if(TypeChambre.SIMPLE.equals(chambre.getTypeC())){
            capMax = 1;
        } else if (TypeChambre.DOUBLE.equals(chambre.getTypeC())) {
            capMax = 2;
        } else if (TypeChambre.TRIPLE.equals(chambre.getTypeC())) {
            capMax = 3;
        }

        long nombreReservations = chambre.getReservations().size();
        if (nombreReservations >= capMax) {
            throw new IllegalStateException("La capacité maximale de la chambre est atteinte.");
        }

        // Gérer la relation ManyToMany
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // Sauvegarder la réservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Ajouter la réservation à la collection de réservations de la chambre et sauvegarder
        chambre.getReservations().add(savedReservation);
        chambreRepository.save(chambre);

        return savedReservation;
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        return null;
    }
}
