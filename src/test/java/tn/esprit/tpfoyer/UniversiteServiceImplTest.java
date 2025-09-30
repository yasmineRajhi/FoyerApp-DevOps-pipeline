package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class UniversiteServiceImplTest {
    @Mock
    UniversiteRepository universiteRepository;

    @Mock
    FoyerRepository foyerRepository;

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Test
    void testAffecterFoyerAUniversite() {
        Foyer foyer = new Foyer();
        Universite universite = new Universite();

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversite("ESPRIT")).thenReturn(universite);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.affecterFoyerAUniversite(1L, "ESPRIT");

        assertThat(result.getFoyer()).isEqualTo(foyer);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        Universite universite = new Universite();
        universite.setFoyer(new Foyer());

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.desaffecterFoyerAUniversite(1L);

        assertThat(result.getFoyer()).isNull();
        verify(universiteRepository, times(1)).save(universite);
    }
}