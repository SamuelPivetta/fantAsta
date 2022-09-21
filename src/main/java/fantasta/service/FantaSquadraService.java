package fantasta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fantasta.exception.FantacalcioException;
import fantasta.model.Calciatore;
import fantasta.model.FantaSquadra;
import fantasta.repository.CalciatoreRepository;
import fantasta.repository.FantaSquadraRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FantaSquadraService {
	
	@Autowired
	FantaSquadraRepository fantaSquadraRepository;
	
	
	public FantaSquadra save(FantaSquadra fantaSquadra) {
		return fantaSquadraRepository.save(fantaSquadra);
	}

	public void delete(Long id) {
		fantaSquadraRepository.deleteById(id);
	}

	public FantaSquadra update(Long id, FantaSquadra fantaSquadra) {
		Optional<FantaSquadra> fantaSquadraResult = fantaSquadraRepository.findById(id);
		if (fantaSquadraResult.isPresent()) {
			FantaSquadra update = fantaSquadraResult.get();
			update.setNome(fantaSquadra.getNome());
			update.setCalciatore(fantaSquadra.getCalciatore());
			return fantaSquadraRepository.save(update);
		}else {
			throw new FantacalcioException("Errore aggiornamento");
		}
		
	}
	
	public Page<FantaSquadra> findAll(Pageable pageable ){
		return fantaSquadraRepository.findAll(pageable);
	}

	public Optional<FantaSquadra> findById(Long id) {
		return fantaSquadraRepository.findById(id);
	}
	
	public List<FantaSquadra> findAll() {
		return fantaSquadraRepository.findAll();
	}

}
