package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

@Tag(name = "Gestion Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {
    IChambreService chambreService;

    // http://localhost:8089/tp-foyer/chambre/retrieve-all-chambres
    @Operation(description = "Récupérer toutes les chambres de la base de données!")
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }

    // http://localhost:8089/tp-foyer/chambre/retrieve-chambre/8
    @Operation(description = "Récupérer la chambre avec l'ID correspondante de la base de données!")
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    // http://localhost:8089/tp-foyer/chambre/add-chambre
    @Operation(description = "Ajouter chambre à la base de données!")
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/tp-foyer/chambre/remove-chambre/{chambre-id}
    @Operation(description = "Enlever la chambre avec l'ID correspondante de la base de données!")
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    // http://localhost:8089/tp-foyer/chambre/modify-chambre
    @Operation(description = "Modifier la chambre!")
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }

    // http://localhost:8089/tp-foyer/chambre/retrieve-all-chambres
    @Operation(description = "Récupérer les chambres par nom d'universite!")
    @GetMapping("/retrieve-chambres-by-uni")
    public List<Chambre> getChambresParUniversite(@PathVariable("chambre-id") String nomUniversite) {
        //List<Chambre> listChambres = chambreService.getChambresParNomUniversite(nomUniversite);
        return null;
    }
}
