package hh.backendohjelmointi.MovieDatabase.domain;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	Set<Movie> findByGenresName(String genre);

	Set<Movie> findByTitleContainingIgnoreCase(String title);
	
}
