package hh.backendohjelmointi.MovieDatabase.domain;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

	Set<User> findByUsernameContainingIgnoreCase(String keyword);
}
