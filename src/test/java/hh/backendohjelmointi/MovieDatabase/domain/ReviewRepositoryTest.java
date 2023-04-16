package hh.backendohjelmointi.MovieDatabase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository repo;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private MovieRepository mrepo;

	@Test
	public void newReview() {
		Movie movie = mrepo.save(new Movie("Test Movie", "Test Director", 1999));
		User user = urepo.save(new User("TestUser", "password", "ADMIN"));
		Review review = new Review(5, "Testreview", movie, user);
		repo.save(review);
		assertThat(review.getReviewId()).isNotNull();
	}

	@Test
	public void deleteReview() {
		Review review = repo.findById(Long.valueOf(1)).get();
		repo.delete(review);
		assertThat(repo.findById(Long.valueOf(1))).isEmpty();
	}

	@Test
	public void findByMovieId() {
		assertThat(repo.findByMovieMovieId(Long.valueOf(1))).isNotEmpty();
	}

}
