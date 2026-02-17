package fabiocarlino.u5l12.repositories;

import fabiocarlino.u5l12.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByUsername(String username);

    Optional<Dipendente> findByEmail(String email);

    boolean existsByEmail(String email);
}
