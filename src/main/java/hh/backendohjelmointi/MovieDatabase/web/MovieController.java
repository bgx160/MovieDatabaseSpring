package hh.backendohjelmointi.MovieDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backendohjelmointi.MovieDatabase.domain.GenreRepository;
import hh.backendohjelmointi.MovieDatabase.domain.Movie;
import hh.backendohjelmointi.MovieDatabase.domain.MovieRepository;
import jakarta.validation.Valid;

@Controller
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private GenreRepository genreRepository;

	@GetMapping("/")
	public String welcome() {
		return "index";
	}

	@GetMapping("/movielist")
	public String movieList(Model m) {
		m.addAttribute("movies", movieRepository.findAll());
		return "movielist";
	}

	@GetMapping("/movielist/search")
	public String searchMovies(Model m, @Param("keyword") String keyword) {
		m.addAttribute("movies", movieRepository.findByTitleContainingIgnoreCase(keyword));
		return "movielist";
	}

	@GetMapping("/movielist/{genre}")
	public String movieListByGenre(@PathVariable("genre") String genre, Model m) {
		m.addAttribute("movies", movieRepository.findByGenresName(genre));
		return "searchresult";
	}

	@GetMapping("/newmovie")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String newMovie(Model m) {
		m.addAttribute("movie", new Movie());
		m.addAttribute("genres", genreRepository.findAll());
		return "addmovie";
	}

	@GetMapping("/movie/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editMovie(@PathVariable Long id, Model m) {
		m.addAttribute("movie", movieRepository.findById(id));
		m.addAttribute("genres", genreRepository.findAll());
		return "editmovie";
	}

	@GetMapping("/movie/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMovie(@PathVariable("id") Long movieId) {
		movieRepository.deleteById(movieId);
		return "redirect:/movielist";
	}

	// save new movie, if movie has validation errors returns new movie form
	@PostMapping("/savemovie")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveMovie(@Valid Movie movie, BindingResult result, Model m) {
		if (!result.hasErrors()) {
			movieRepository.save(movie);
			return "redirect:/movielist";
		} else {
			m.addAttribute("genres", genreRepository.findAll());
			return "addmovie";
		}
	}

	// save edited movie, if movie has validation errors returns edit movie form
	@PostMapping("/editmovie")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editMovie(@Valid Movie movie, BindingResult result, Model m) {
		if (!result.hasErrors()) {
			movieRepository.save(movie);
			return "redirect:/movielist";
		} else {
			m.addAttribute("genres", genreRepository.findAll());
			return "editmovie";
		}
	}

}
