package fantasta.service.secuirty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fantasta.model.security.User;
import fantasta.repository.security.UserRepository;






@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
