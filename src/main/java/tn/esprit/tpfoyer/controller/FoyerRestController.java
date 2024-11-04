package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;

@Tag(name = "Gestion Foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {

     IFoyerService foyerService;

    // http://localhost:8089/tp-foyer/foyer/retrieve-all-foyers
    @Operation(description = "Récupérer tous les foyers de la base de données!")
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyer() {
        List<Foyer> listFoyer = foyerService.retrieveAllFoyer();
        return listFoyer;
    }

    // http://localhost:8089/tp-foyer/foyer/retrieve-foyer/8
    @Operation(description = "Récupérer le foyer avec l'ID correspondant de la base de données!")
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long uniId) {
        Foyer foyer = foyerService.retrieveFoyer(uniId);
        return foyer;
    }

    // http://localhost:8089/tp-foyer/foyer/add-foyer
    @Operation(description = "Ajouter foyer à la base de données!")
    @PostMapping("/add-foyer")
    public Foyer addUniversite(@RequestBody Foyer f){
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    // http://localhost:8089/tp-foyer/foyer/remove-foyer/{foyer-id}
    @Operation(description = "Enlever le foyer avec l'ID correspondant de la base de données!")
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    // http://localhost:8089/tp-foyer/foyer/modify-foyer
    @Operation(description = "Modifier le foyer!")
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer u) {
        Foyer foyer = foyerService.modifyFoyer(u);
        return foyer;
    }

    // http://localhost:8089/tp-foyer/foyer/add/{idUniversite}
    @Operation(description = "ajouter un Foyer Et l'affecter à une Universite!")
    @PostMapping("/add/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @PathVariable("idUniversite") long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }
}