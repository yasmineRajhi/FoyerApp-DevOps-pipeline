package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlocServiceImplTest {
    @Mock
    BlocRepository blocRepository;

    @Mock
    ChambreRepository chambreRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    @Test
    void testRetrieveAllBlocs() {
        Bloc bloc = new Bloc();
        when(blocRepository.findAll()).thenReturn(List.of(bloc));

        List<Bloc> result = blocService.retrieveAllBlocs();

        assertThat(result).hasSize(1);
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testAddBloc() {
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.addBloc(bloc);

        assertThat(result).isNotNull();
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testAffecterChambresABloc() {
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        Chambre chambre = new Chambre();
        chambre.setIdChambre(10L);

        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));
        when(chambreRepository.findById(10L)).thenReturn(Optional.of(chambre));
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.affecterChambresABloc(List.of(10L), 1L);

        assertThat(result).isNotNull();
        verify(chambreRepository, times(1)).save(chambre);
        verify(blocRepository, times(1)).save(bloc);
    }
}
