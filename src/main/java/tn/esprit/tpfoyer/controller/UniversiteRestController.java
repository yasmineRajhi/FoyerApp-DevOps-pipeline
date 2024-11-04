package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@Tag(name = "Gestion Universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
    IUniversiteService universiteService;

    // http://localhost:8089/tp-foyer/universite/retrieve-all-universites
    @Operation(description = "Récupérer toutes les universites de la base de données!")
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getReservations() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }

    // http://localhost:8089/tp-foyer/universite/retrieve-universite/8
    @Operation(description = "Récupérer l'universite avec l'ID correspondante de la base de données!")
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uniId) {
        Universite universite = universiteService.retrieveUniversite(uniId);
        return universite;
    }

    // http://localhost:8089/tp-foyer/universite/add-universite
    @Operation(description = "Ajouter universite à la base de données!")
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u){
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    // http://localhost:8089/tp-foyer/universite/remove-universite/{universite-id}
    @Operation(description = "Enlever l'universite avec l'ID correspondante de la base de données!")
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uniId) {
        universiteService.removeUniversite(uniId);
    }

    // http://localhost:8089/tp-foyer/universite/modify-universite
    @Operation(description = "Modifier l'universite!")
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.modifyUniversite(u);
        return universite;
    }

    // http://localhost:8089/tp-foyer/universite/affecterFoyer/{idFoyer}/{nomUniversite}
    @Operation(description = "Affecter Foyer à partir d'une université!")
    @PutMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable Long idFoyer, @PathVariable String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    // http://localhost:8089/tp-foyer/universite/desaffecterFoyer/{idUniversite}
    @Operation(description = "Désaffecter Foyer à partir d'une université!")
    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable Long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}
