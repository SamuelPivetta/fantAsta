package fantasta.controller.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fantasta.model.Calciatore;
import fantasta.model.FantaSquadra;
import fantasta.repository.CalciatoreRepository;
import fantasta.repository.FantaSquadraRepository;
import fantasta.service.CalciatoreService;
import fantasta.service.FantaSquadraService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/calciatore")
public class CalciatoreControllerWeb {

	
	@Autowired
	CalciatoreService calciatoreService;
	
	@Autowired
	FantaSquadraService fantaSquadraService;
	
//	@Autowired
//	CalciatoreRepository calciatoreRepository;
//	@Autowired
//	FantaSquadraRepository fantaSquadraRepository;
	

	
	
	@GetMapping("/mostraelenco")
	public ModelAndView mostraElencoCalciatori(Pageable pageable) {
		log.info("Test elenco calciatori su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencocalciatori");
		view.addObject("listaCalciatori", calciatoreService.findAll(pageable));
	
		return view;
	}
	
	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Calciatore calciatore, Model model) {
		log.info("Form aggiungi calciatore");
		model.addAttribute("listaFantaSquadra", fantaSquadraService.findAll());
		return "formCalciatore";
	}

	@PostMapping("/addCalciatore")
	public String aggiungiCalciatore(@Valid Calciatore calciatore, BindingResult result, Model model) {
		log.info("Aggiungi calciatore");
		if (result.hasErrors()) {
			model.addAttribute("listaCalciatore", calciatoreService.findAll(null));
			return "formCalciatore";
		}
		calciatoreService.save(calciatore);

		return "redirect:/calciatore/mostraelenco";
	}
	
	@GetMapping("/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
		log.info("Test mostra form aggiorna calciatore");
		Optional<Calciatore> calciatoreAggiorna = calciatoreService.findById(id);
		if (calciatoreAggiorna.isPresent()) {
			ModelAndView view = new ModelAndView("editCalciatore");
			view.addObject("calciatore", calciatoreAggiorna.get());
			
			return view;
		}

		return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornacalciatore/{id}")
	public String aggiornaCalciatore(@PathVariable Long id, Calciatore calciatore, BindingResult result, Model model) {
		calciatoreService.update(id, calciatore);

		log.info("Calciatore aggiornato");
		return "redirect:/calciatore/mostraelenco";

	}

	@GetMapping("/eliminacalciatore/{id}")
	public ModelAndView eliminaCalciatore(@PathVariable Long id, Model model,Pageable pageable) {
		Optional<Calciatore> calciatoreDelete = calciatoreService.findById(id);
		if (calciatoreDelete.isPresent()) {
			calciatoreService.delete(calciatoreDelete.get().getId());
			ModelAndView view = new ModelAndView("elencocalciatori");
			view.addObject("listaCalciatori", calciatoreService.findAll(pageable));
			return view;

		} else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}

		
	}
	
	@GetMapping("/asta")
	public ModelAndView Asta(Pageable pageable,Calciatore calciatore, Model model) {
		log.info("Test asta Thymeleaf");
		model.addAttribute("listaFantaSquadra", fantaSquadraService.findAll());
		ModelAndView view = new ModelAndView("asta");
		view.addObject("listaCalciatori",calciatoreService.findAll(pageable));
		
		return view;
	}
	
	@PostMapping("/addCalciatoreASquadra")
	public String aggiungiCalciatoreASquadra(Long idCalciatore,Long idFantaSquadra,
			Model model, Calciatore calciatore, FantaSquadra fantaSquadra) {
		log.info("Aggiungi calciatore a squadra");
		
		idCalciatore = calciatore.getId();
		idFantaSquadra = fantaSquadra.getId();
		calciatoreService.addCalciatoreASquadra(idCalciatore, idFantaSquadra);
		

		return "redirect:/calciatore/asta";
	}
	
	@GetMapping("/prova")
	public ModelAndView Prova() {
		log.info("Test asta Thymeleaf");
		ModelAndView view = new ModelAndView("prova");
		view.addObject("prova");
	
		return view;
	}
	
//	@GetMapping("/astaprova")
//	public ModelAndView astaProva(Pageable pageable) {
//		log.info("Test asta Thymeleaf");
//		ModelAndView view = new ModelAndView("elencocalciatori");
//		view.addObject("asta", calciatoreService.findAll(pageable));
//	
//		return view;
//	}
}
