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

import fantasta.model.Calciatore;
import fantasta.model.FantaSquadra;
import fantasta.service.CalciatoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class CalciatoreController {
	
	@Autowired
	CalciatoreService calciatoreService;
	
	@PostMapping(path = "/calciatore")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci calciatore")
	public ResponseEntity<Calciatore> save(@RequestBody Calciatore calciatore) {
		Calciatore save = calciatoreService.save(calciatore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/calciatore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina calciatore")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		calciatoreService.delete(id);
		return new ResponseEntity<>("Calciatore cancellato!", HttpStatus.OK);

	}
	
	@GetMapping(path = "/calciatore")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista  calciatori")
	public ResponseEntity<Page<Calciatore>> findAll(Pageable pageable) {
		Page<Calciatore> findAll = calciatoreService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping(path = "/calciatore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna calciatore")
	public ResponseEntity<Calciatore> update(@PathVariable Long id, @RequestBody Calciatore calciatore) {
		Calciatore save = calciatoreService.update(id, calciatore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PostMapping(path = "/calciatoresquadra/{idCalciatore}/{idFantaSquadra}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci calciatore a squadra")
	public ResponseEntity<String> aggiungiCalciatoreASquadra(@PathVariable Long idCalciatore,@PathVariable Long idFantaSquadra) {
		calciatoreService.addCalciatoreASquadra(idCalciatore, idFantaSquadra);
		return new ResponseEntity<>("Calciatore aggiunto alla FantaSquadra!", HttpStatus.OK);

	}
	
	

}
