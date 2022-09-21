package fantasta.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fantasta.model.Calciatore;
import fantasta.model.Club;
import fantasta.model.FantaSquadra;
import fantasta.model.TipoRuolo;
import fantasta.model.security.Role;
import fantasta.model.security.Roles;
import fantasta.model.security.User;
import fantasta.repository.CalciatoreRepository;
import fantasta.repository.FantaSquadraRepository;
import fantasta.repository.security.RoleRepository;
import fantasta.repository.security.UserRepository;





@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CalciatoreRepository calciatoreRepository;
	
	@Autowired
	FantaSquadraRepository fantaSquadraRepository;
	


	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		Role roleU = new Role();
		roleU.setRoleName(Roles.ROLE_USER);
		Role roleA = new Role();
		roleA.setRoleName(Roles.ROLE_ADMIN);
		
	
		User admin = new User();
		Set<Role> rolesA = new HashSet<>(); 
		rolesA.add(roleA);
		rolesA.add(roleU);
		admin.setUserName("admin");
		admin.setPassword(bCrypt.encode("admin"));
		admin.setEmail("admin@admin.it");
		admin.setRoles(rolesA);
		admin.setActive(true);
		
		
		User user1 = new User();
		Set<Role> rolesU = new HashSet<>(); 
		rolesU.add(roleU);
		user1.setUserName("samuel");
		user1.setPassword(bCrypt.encode("password"));
		user1.setEmail("samuel_91_@hotmail.it");
		user1.setRoles(rolesU);
		user1.setActive(true);
		
		
		roleRepository.save(roleU);
		roleRepository.save(roleA);
		userRepository.save(admin);
		userRepository.save(user1);


		List<FantaSquadra> listaFantaSquadra = initFantaSquadra();
		FantaSquadra f1 = listaFantaSquadra.get(0);
		FantaSquadra f2 = listaFantaSquadra.get(1);
		FantaSquadra f3 = listaFantaSquadra.get(2);
		
		fantaSquadraRepository.save(f1);
		fantaSquadraRepository.save(f2);
		fantaSquadraRepository.save(f3);
		
		
		List<Calciatore> listaCalciatori = initCalciatore();
		Calciatore c1 = listaCalciatori.get(0);
		Calciatore c2 = listaCalciatori.get(1);
		Calciatore c3 = listaCalciatori.get(2);
		
		calciatoreRepository.save(c1);
		calciatoreRepository.save(c2);
		calciatoreRepository.save(c3);

		
	}

	private List<Calciatore> initCalciatore(){
		List<Calciatore> listaCalciatori = new ArrayList<>();
		Calciatore c1 = new Calciatore();
		c1.setNome("Giorgio");
		c1.setCognome("Chiellini");
		c1.setClub(Club.JUVENTUS);
		c1.setRuolo(TipoRuolo.DIFENSORE);
		c1.setValore(12);
		
		Calciatore c2 = new Calciatore();
		c2.setNome("Ciro");
		c2.setCognome("Immobile");
		c2.setClub(Club.LAZIO);
		c2.setRuolo(TipoRuolo.ATTACCANTE);
		c2.setValore(34);
		
		Calciatore c3 = new Calciatore();
		c3.setNome("Federico");
		c3.setCognome("Chiesa");
		c3.setClub(Club.JUVENTUS);
		c3.setRuolo(TipoRuolo.CENTROCAMPISTA);
		c3.setValore(29);
		
		listaCalciatori.add(c1);
		listaCalciatori.add(c2);
		listaCalciatori.add(c3);
		
		
		return listaCalciatori;
		
	}
	private List<FantaSquadra> initFantaSquadra(){
		List<FantaSquadra> listaFantaSquadra = new ArrayList<>();
		FantaSquadra f1 = new FantaSquadra();
		f1.setNome("FantaSquadra1");
		
		FantaSquadra f2 = new FantaSquadra();
		f2.setNome("FantaSquadra2");
		
		FantaSquadra f3 = new FantaSquadra();
		f3.setNome("FantaSquadra3");
		
		listaFantaSquadra.add(f1);
		listaFantaSquadra.add(f2);
		listaFantaSquadra.add(f3);
		
		return listaFantaSquadra;
		
	}

}
