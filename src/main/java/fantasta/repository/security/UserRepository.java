package fantasta.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fantasta.model.security.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findById(Integer id);

	public Optional<User> findByUserName(String userName);

	public boolean existsByEmail(String email);

	public boolean existsByUserName(String userName);
}
