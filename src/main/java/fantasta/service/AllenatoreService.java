package fantasta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fantasta.exception.FantacalcioException;
import fantasta.model.Allenatore;
import fantasta.model.Calciatore;
import fantasta.model.FantaSquadra;
import fantasta.repository.AllenatoreRepository;
import fantasta.repository.FantaSquadraRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AllenatoreService {
	
	@Autowired
	AllenatoreRepository allenatoreRepository;
	@Autowired
	FantaSquadraRepository fantaSquadraRepository;



public Allenatore save(Allenatore allenatore) {
	return allenatoreRepository.save(allenatore);
}

public void delete(Long id) {
	allenatoreRepository.deleteById(id);
}
public Allenatore update(Long id, Allenatore allenatore) {
	Optional<Allenatore> allenatoreResult = allenatoreRepository.findById(id);
	if (allenatoreResult.isPresent()) {
		Allenatore update = allenatoreResult.get();
		update.setNome(allenatore.getNome());
		update.setEmail(allenatore.getEmail());
		update.setFantaSquadra(allenatore.getFantaSquadra());
		return allenatoreRepository.save(update);
	} else {
		throw new FantacalcioException("Errore aggiornamento");
	}
}
	
	public Page<Allenatore> findAll(Pageable pageable) {
		return allenatoreRepository.findAll(pageable);
	}

	public Optional<Allenatore> findById(Long id) {
		return allenatoreRepository.findById(id);
	}


	public void addAllenatoreAFantaSquadra(Long idAllenatore,Long idFantaSquadra) {
		Optional<Allenatore> allenatoreId = allenatoreRepository.findById(idAllenatore);
		Optional<FantaSquadra> fantaSquadraId = fantaSquadraRepository.findById(idFantaSquadra);
		if (allenatoreId.isPresent() && fantaSquadraId.isPresent()) {
			Allenatore aggiungiAllenatore = allenatoreId.get();
			FantaSquadra aggiungiFantaSquadra = fantaSquadraId.get();
			aggiungiAllenatore.setFantaSquadra(aggiungiFantaSquadra);
			allenatoreRepository.save(aggiungiAllenatore);
		}
	}

}
