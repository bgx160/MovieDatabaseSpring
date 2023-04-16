package hh.backendohjelmointi.MovieDatabase.domain;

import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genreId;

	@NotEmpty
	private String name;

	@ManyToMany(mappedBy = "genres")
	private Set<Movie> movies = new HashSet<>();

	@JsonIgnoreProperties("genres")
	public Set<Movie> getMovies() {
		return movies;
	}

	public Genre() {
		super();
	}

	public Genre(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public void removeMovie(Movie movie) {
		this.movies.remove(movie);
		movie.getGenres().remove(this);
	}

}
