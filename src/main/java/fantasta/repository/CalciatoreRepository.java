package fantasta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.Calciatore;


public interface CalciatoreRepository extends JpaRepository<Calciatore, Long>{


	public Page<Calciatore> findAll(Pageable pageable);
}
