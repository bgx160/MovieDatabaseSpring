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

import hh.backendohjelmointi.MovieDatabase.domain.Genre;
import hh.backendohjelmointi.MovieDatabase.domain.GenreRepository;

@RestController
public class GenreRestController {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@GetMapping("/genres")
	public List<Genre> genreList() {
		return (List<Genre>) genreRepository.findAll();
	}
	
	@GetMapping("/genres/{id}")
	public Optional<Genre> genreById(@PathVariable("id") Long id) {
		return genreRepository.findById(id);
	}
	
	@PostMapping("/genres")
	public Genre addGenre(@RequestBody Genre genre) {
		return genreRepository.save(genre);
	}
	
	@DeleteMapping("/genres/{id}")
	public void deleteGenre(@PathVariable("id") Long id) {
		genreRepository.deleteById(id);
	}

}
