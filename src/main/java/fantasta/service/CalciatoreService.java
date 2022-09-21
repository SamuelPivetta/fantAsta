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
public class CalciatoreService {

	@Autowired
	CalciatoreRepository calciatoreRepository;
	@Autowired
	FantaSquadraRepository fantaSquadraRepository;


	
	public Calciatore save(Calciatore calciatore) {
		return calciatoreRepository.save(calciatore);
	}

	public void delete(Long id) {
		calciatoreRepository.deleteById(id);
	}

	public Calciatore update(Long id, Calciatore calciatore) {
		Optional<Calciatore> calciatoreResult = calciatoreRepository.findById(id);
		if (calciatoreResult.isPresent()) {
			Calciatore update = calciatoreResult.get();
			update.setNome(calciatore.getNome());
			update.setCognome(calciatore.getCognome());
			update.setRuolo(calciatore.getRuolo());
			update.setClub(calciatore.getClub());
			update.setValore(calciatore.getValore());
			update.setFantaSquadra(calciatore.getFantaSquadra());
			return calciatoreRepository.save(update);
		} else {
			throw new FantacalcioException("Errore aggiornamento");
		}

	}

	public Page<Calciatore> findAll(Pageable pageable) {
		return calciatoreRepository.findAll(pageable);
	}

	public Optional<Calciatore> findById(Long id) {
		return calciatoreRepository.findById(id);
	}

	public void addCalciatoreASquadra(Long idCalciatore, Long idFantaSquadra) {
		Optional<Calciatore> calciatoreId = calciatoreRepository.findById(idCalciatore);
		Optional<FantaSquadra> fantaSquadraId = fantaSquadraRepository.findById(idFantaSquadra);
		if (calciatoreId.isPresent() && fantaSquadraId.isPresent()) {
			Calciatore aggiungiCalciatoreId = calciatoreId.get();
			FantaSquadra aggiungiFantaSquadraId = fantaSquadraId.get();
			aggiungiCalciatoreId.setFantaSquadra(aggiungiFantaSquadraId);
			List<Calciatore> listaCalciatore = aggiungiFantaSquadraId.getCalciatore();
			listaCalciatore.add(aggiungiCalciatoreId);
			aggiungiFantaSquadraId.setCalciatore(listaCalciatore);
			fantaSquadraRepository.save(aggiungiFantaSquadraId);
			calciatoreRepository.save(aggiungiCalciatoreId);
			

		}else {
			throw new FantacalcioException("Calciatore non inserito");
		}

	}
}
