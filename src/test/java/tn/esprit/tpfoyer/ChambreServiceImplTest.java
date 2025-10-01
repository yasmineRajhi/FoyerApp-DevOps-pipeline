package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChambreServiceImplTest {
    @Mock
    ChambreRepository chambreRepository;

    @InjectMocks
    ChambreServiceImpl chambreService;

    @Test
    void testRetrieveAllChambres() {
        Chambre chambre = new Chambre();
        when(chambreRepository.findAll()).thenReturn(List.of(chambre));

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertThat(result).hasSize(1);
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testAddChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertThat(result).isNotNull();
        verify(chambreRepository, times(1)).save(chambre);
    }
}