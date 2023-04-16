package hh.backendohjelmointi.MovieDatabase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository repo;
	
	@Test
	@Rollback(false)
	public void createGenre() {
		Genre genre = new Genre("Test Genre");
		repo.save(genre);
		assertThat(genre).isNotNull();
	}
	
	public void deleteGenre() {
		Genre genre = repo.findById(Long.valueOf(1)).get();
		repo.delete(genre);
		assertThat(repo.findById(Long.valueOf(1))).isEmpty();
	}
	
	public void findAllGenres() {
		assertThat(repo.findAll()).isNotEmpty();
	}

}
