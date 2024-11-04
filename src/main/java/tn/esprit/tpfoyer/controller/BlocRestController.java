package tn.esprit.tpfoyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@Tag(name = "Gestion Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {
    IBlocService blocService;

    // http://localhost:8089/tp-foyer/bloc/retrieve-all-blocs
    @Operation(description = "Récupérer tous les blocs de la base de données!")
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    // http://localhost:8089/tp-foyer/bloc/retrieve-bloc/8
    @Operation(description = "Récupérer le bloc avec l'ID correspondante de la base de données!")
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    // http://localhost:8089/tp-foyer/bloc/add-bloc
    @Operation(description = "Ajouter bloc à la base de données!")
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    // http://localhost:8089/tp-foyer/bloc/remove-bloc/{bloc-id}
    @Operation(description = "Enlever le bloc avec l'ID correspondante de la base de données!")
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    // http://localhost:8089/tp-foyer/bloc/modify-bloc
    @Operation(description = "Modifier le bloc!")
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc bloc) {
        return blocService.modifyBloc(bloc);
    }
    // http://localhost:8089/tp-foyer/bloc/affecterChambres/{idBloc}
    @Operation(description = "Affecter chambre à bloc!")
    @PutMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @PathVariable Long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
}
