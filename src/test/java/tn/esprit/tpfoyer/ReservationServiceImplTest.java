package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceImplTest {
    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ChambreRepository chambreRepository;

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Test
    void testAjouterReservation() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setTypeC(TypeChambre.SIMPLE);
        chambre.setReservations(new HashSet<>());
        Bloc bloc = new Bloc();
        bloc.setNomBloc("A");
        chambre.setBl(bloc);

        Etudiant etudiant = new Etudiant();
        etudiant.setCin(123L);

        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));
        when(etudiantRepository.findByCin(123L)).thenReturn(etudiant);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArgument(0));

        Reservation result = reservationService.ajouterReservation(1L, 123L);

        assertThat(result).isNotNull();
        assertThat(result.getEtudiants()).contains(etudiant);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(chambreRepository, times(1)).save(chambre);
    }
}
