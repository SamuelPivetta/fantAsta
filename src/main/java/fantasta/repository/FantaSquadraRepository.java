package fantasta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.FantaSquadra;

public interface FantaSquadraRepository extends JpaRepository<FantaSquadra, Long> {

	public Page<FantaSquadra> findAll(Pageable pageable);
}
