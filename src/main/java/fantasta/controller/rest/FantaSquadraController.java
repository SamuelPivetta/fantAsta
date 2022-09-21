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

import fantasta.model.FantaSquadra;
import fantasta.service.FantaSquadraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class FantaSquadraController {
	
	@Autowired
	FantaSquadraService fantaSquadraService;
	
	@PostMapping(path = "/fantasquadra")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisci fantaSquadra")
	public ResponseEntity<FantaSquadra> save(@RequestBody FantaSquadra fantaSquadra) {
		FantaSquadra save = fantaSquadraService.save(fantaSquadra);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/fantasquadra/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina fantaSquadra")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fantaSquadraService.delete(id);
		return new ResponseEntity<>("FantaSqudra cancellata!", HttpStatus.OK);

	}
	
	@GetMapping(path = "/fantasquadra")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista FantaSquadre")
	public ResponseEntity<Page<FantaSquadra>> findAll(Pageable pageable) {
		Page<FantaSquadra> findAll = fantaSquadraService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping(path = "/fantasquadra/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna fantaSquadra")
	public ResponseEntity<FantaSquadra> update(@PathVariable Long id, @RequestBody FantaSquadra fantaSquadra) {
		FantaSquadra save = fantaSquadraService.update(id, fantaSquadra);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	

}
