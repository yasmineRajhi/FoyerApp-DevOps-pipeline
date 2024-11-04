package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc retrieveBloc(Long blocId);

    Bloc addBloc(Bloc bloc);

    void removeBloc(Long blocId);

    Bloc modifyBloc(Bloc bloc);
    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
}
