package hh.backendohjelmointi.MovieDatabase.web;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backendohjelmointi.MovieDatabase.domain.Genre;
import hh.backendohjelmointi.MovieDatabase.domain.GenreRepository;
import hh.backendohjelmointi.MovieDatabase.domain.Movie;
import jakarta.validation.Valid;

@Controller
public class GenreController {

	@Autowired
	private GenreRepository genreRepository;

	@GetMapping("/genrelist")
	public String genreList(Model m) {
		m.addAttribute("genres", genreRepository.findAll());
		return "genrelist";
	}

	@GetMapping("/newgenre")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String newGenre(Model m) {
		m.addAttribute("genre", new Genre());
		return "addgenre";
	}

	@GetMapping("genre/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGenre(@PathVariable("id") Long genreId) {
		Genre genre = genreRepository.findById(genreId).get();

		// iterate over new HashSet to avoid ConcurrentModificationException
		for (Movie movie : new HashSet<Movie>(genre.getMovies())) {
			movie.removeGenre(genre);
		}

		genreRepository.deleteById(genreId);
		return "redirect:/genrelist";
	}

	@PostMapping("/savegenre")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveGenre(@Valid Genre genre, BindingResult result) {
		if (!result.hasErrors()) {
			genreRepository.save(genre);
			return "redirect:/genrelist"; // TODO: redirect last page
		} else {
			return "redirect:/newgenre";
		}

	}
}
