package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion Reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    IReservationService reservationService;

    // http://localhost:8089/tp-foyer/reservation/retrieve-all-reservations
    @Operation(description = "Récupérer toutes les reservations de la base de données!")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }

    // http://localhost:8089/tp-foyer/reservation/retrieve-reservation/8
    @Operation(description = "Récupérer la reservation avec l'ID correspondante de la base de données!")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reId) {
        Reservation reservation = reservationService.retrieveReservation(reId);
        return reservation;
    }

    // http://localhost:8089/tp-foyer/reservation/add-reservation
    @Operation(description = "Ajouter reservation à la base de données!")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r){
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    // http://localhost:8089/tp-foyer/reservation/remove-reservation/{reservation-id}
    @Operation(description = "Enlever la reservation avec l'ID correspondante de la base de données!")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reId) {
        reservationService.removeReservation(reId);
    }

    // http://localhost:8089/tp-foyer/reservation/modify-reservation
    @Operation(description = "Modifier la reservation!")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return reservation;
    }

    // http://localhost:8089/tp-foyer/reservation/add/{idChambre}/{cin}
    @Operation(description = "Ajouter une reservation!")
    @PostMapping("/add/{idChambre}/{cin}")
    public Reservation ajouterReservation(@PathVariable Long idChambre, @PathVariable Long cin) {
        return reservationService.ajouterReservation(idChambre, cin);
    }

    // http://localhost:8089/tp-foyer/reservation/annulerReservation/{cin}
    @Operation(description = "Annuler une reservation!")
    @PutMapping("/annulerReservation/{cin}")
    public Reservation annulerReservation(@PathVariable Long cin) {
        return reservationService.annulerReservation(cin);
    }
}
