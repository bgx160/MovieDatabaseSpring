package hh.backendohjelmointi.MovieDatabase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;

	@Test
	public void createUser() {
		User user = new User("TestUser", "password", "ADMIN");
		repo.save(user);
		assertThat(user.getUserId()).isNotNull();
	}

	public void deleteUser() {
		User user = repo.findById(Long.valueOf(1)).get();
		repo.delete(user);
		assertThat(repo.findById(Long.valueOf(1))).isEmpty();
	}

	@Test
	public void findByUsername() {
		User user = new User("TestUser", "password", "ADMIN");
		repo.save(user);
		assertThat(repo.findByUsername("TestUser")).isEqualTo(user);
	}
}