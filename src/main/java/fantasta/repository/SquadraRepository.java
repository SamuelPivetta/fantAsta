package fantasta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.FantaSquadra;

public interface SquadraRepository extends JpaRepository<FantaSquadra, Long> {

}
