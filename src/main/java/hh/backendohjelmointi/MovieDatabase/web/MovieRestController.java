package hh.backendohjelmointi.MovieDatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.backendohjelmointi.MovieDatabase.domain.Movie;
import hh.backendohjelmointi.MovieDatabase.domain.MovieRepository;

@RestController
public class MovieRestController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/movies")
	public List<Movie> movielist() {
		return (List<Movie>) movieRepository.findAll();
	}

	@GetMapping("/movies/{id}")
	public Optional<Movie> movieById(@PathVariable("id") Long id) {
		return (Optional<Movie>) movieRepository.findById(id);
	}

	@PostMapping("/movies")
	public Movie addMovie(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}

	@DeleteMapping("/movies/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieRepository.deleteById(id);
	}
}
