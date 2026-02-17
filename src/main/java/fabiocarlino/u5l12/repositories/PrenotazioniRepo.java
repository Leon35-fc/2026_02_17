package fabiocarlino.u5l12.repositories;

import fabiocarlino.u5l12.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrenotazioniRepo extends JpaRepository<Prenotazione, UUID> {
    Optional<Prenotazione> findById(UUID id);

    boolean existsByDipendenteIdAndViaggio_DataViaggio(UUID dipendenteId, LocalDate dataViaggio);
}
