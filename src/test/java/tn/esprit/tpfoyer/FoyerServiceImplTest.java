package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.IFoyerServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class FoyerServiceImplTest {
    @Mock
    FoyerRepository foyerRepository;

    @Mock
    UniversiteRepository universiteRepository;

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    IFoyerServiceImpl foyerService;

    @Test
    void testRetrieveAllFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.findAll()).thenReturn(List.of(foyer));

        List<Foyer> result = foyerService.retrieveAllFoyer();

        assertThat(result).hasSize(1);
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testAjouterFoyerEtAffecterAUniversite() {
        Foyer foyer = new Foyer();
        Bloc bloc = new Bloc();
        foyer.setBlocs(Set.of(bloc));
        Universite universite = new Universite();

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, 1L);

        assertThat(result).isNotNull();
        verify(blocRepository, times(1)).save(bloc);
        verify(universiteRepository, times(1)).save(universite);
    }
}