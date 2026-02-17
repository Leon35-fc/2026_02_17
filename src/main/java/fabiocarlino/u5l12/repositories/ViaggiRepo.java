package fabiocarlino.u5l12.repositories;

import fabiocarlino.u5l12.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ViaggiRepo extends JpaRepository<Viaggio, UUID> {
    Optional<Viaggio> findById(UUID id);

    Optional<Viaggio> findByData(LocalDate data);
}
