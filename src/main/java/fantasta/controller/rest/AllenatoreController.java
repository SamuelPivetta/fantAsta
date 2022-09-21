package fantasta.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fantasta.model.Allenatore;
import fantasta.model.Calciatore;
import fantasta.service.AllenatoreService;
import fantasta.service.CalciatoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class AllenatoreController {
	
	@Autowired
	AllenatoreService allenatoreService;
	
	@PostMapping(path = "/allenatore")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci allenatore")
	public ResponseEntity<Allenatore> save(@RequestBody Allenatore allenatore) {
		Allenatore save = allenatoreService.save(allenatore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/allenatore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina allenatore")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		allenatoreService.delete(id);
		return new ResponseEntity<>("Allenatore cancellato!", HttpStatus.OK);

	}
	
	@GetMapping(path = "/allenatore")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  allenatori")
	public ResponseEntity<Page<Allenatore>> findAll(Pageable pageable) {
		Page<Allenatore> findAll = allenatoreService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping(path = "/allenatore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna allenatore")
	public ResponseEntity<Allenatore> update(@PathVariable Long id, @RequestBody Allenatore allenatore) {
		Allenatore save = allenatoreService.update(id, allenatore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PostMapping(path = "/allenatoreasquadra/{idAllenatore}/{idFantaSquadra}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci allenatore a squadra")
	public ResponseEntity<String> addAllenatoreAFantaSquadra(@PathVariable Long idAllenatore,@PathVariable Long idFantaSquadra) {
		allenatoreService.addAllenatoreAFantaSquadra(idAllenatore, idFantaSquadra);
		return new ResponseEntity<>("Allenatore aggiunto alla FantaSquadra!", HttpStatus.OK);

	}
	
	

}

