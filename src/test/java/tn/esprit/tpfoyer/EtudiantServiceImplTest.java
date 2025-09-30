package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class EtudiantServiceImplTest {
    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    @Test
    void testRetrieveAllEtudiants() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(List.of(etudiant));

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertThat(result).hasSize(1);
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertThat(result).isNotNull();
        verify(etudiantRepository, times(1)).save(etudiant);
    }
}