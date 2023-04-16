package hh.backendohjelmointi.MovieDatabase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository repo;

	@Autowired
	private GenreRepository grepo;

	@Test
	@Rollback(false)
	public void createMovie() {
		Movie movie = new Movie("Test Movie", "Test Director", 1999);
		repo.save(movie);

		assertThat(movie.getMovieId()).isNotNull();
	}

	@Test
	public void deleteMovie() {
		Movie movie = repo.findById(Long.valueOf(1)).get();
		repo.delete(movie);

		assertThat(repo.findById(Long.valueOf(1))).isEmpty();
	}

	@Test
	public void findByGenresName() {
		Genre genre = grepo.save(new Genre("Test genre"));
		Movie movie = new Movie("Test Movie", "Test Director", 1999);
		movie.addGenre(genre);
		Movie movie2 = repo.save(new Movie("Test", "Test", 1999));
		repo.save(movie);

		assertThat(repo.findByGenresName("Test genre")).contains(movie);
		assertThat(repo.findByGenresName("Test genre")).doesNotContain(movie2);
	}

	@Test
	public void findByTitleContaining() {

		Set<Movie> movies = repo.findByTitleContainingIgnoreCase("Test");

		assertThat(movies).isNotEmpty();
		assertThat(repo.findAll().toString()).isEqualTo(repo.findByTitleContainingIgnoreCase("").toString());
	}
}
