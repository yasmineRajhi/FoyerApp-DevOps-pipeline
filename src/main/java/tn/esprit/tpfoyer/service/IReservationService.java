package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.util.List;

public interface IReservationService {
    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(Long idReservation);
    public Reservation addReservation(Reservation r);
    public void removeReservation(Long idReservation);
    public Reservation modifyReservation(Reservation reservation);
    Reservation ajouterReservation (Long idChambre, Long cinEtudiant);
    Reservation annulerReservation (long cinEtudiant);
}
