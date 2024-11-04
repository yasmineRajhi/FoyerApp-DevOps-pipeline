package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.Map;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public Etudiant findByCin(Long cinEtudiant);
}
