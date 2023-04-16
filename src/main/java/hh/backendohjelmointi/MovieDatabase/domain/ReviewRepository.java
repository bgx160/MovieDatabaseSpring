package hh.backendohjelmointi.MovieDatabase.domain;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Set<Review> findByMovieMovieId(Long movieId);
	
}