package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.*;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    //Chambre findByReservationsContains(Reservation reservation);
}