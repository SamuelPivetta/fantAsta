package fantasta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.Allenatore;
import fantasta.model.Calciatore;

public interface AllenatoreRepository extends JpaRepository<Allenatore, Long>{

	public Page<Allenatore> findAll(Pageable pageable);
}
