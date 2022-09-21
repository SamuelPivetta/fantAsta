package fantasta.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.security.Role;
import fantasta.model.security.Roles;




public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
