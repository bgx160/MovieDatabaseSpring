package hh.backendohjelmointi.MovieDatabase.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movieId;
	
	@Column(name = "`year`")
	@Min(value = 1888, message = "must be atleast 1888")
	private int year;

	@JsonIgnoreProperties("movies")
	@ManyToMany
	@JoinTable(name = "movie_genre",
	joinColumns = @JoinColumn(name = "movieId"),
	inverseJoinColumns = @JoinColumn(name = "genreId"))
	private Set<Genre> genres = new HashSet<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
	private Set<Review> reviews = new HashSet<>();
	
	@NotEmpty(message = "movie must have a title")
	private String title;
	
	@NotEmpty(message = "movie must have a director")
	private String director;

	public Movie() {
		super();
	}

	public Movie(String title, String director, int year) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
	}
	
//	public Movie(String title, String director, int year, Set<Genre> genres) {
//		super();
//		this.title = title;
//		this.director = director;
//		this.year = year;
//		this.genres = genres;
//	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public int getYear() {
		return year;
	}

	public Long getMovieId() {
		return movieId;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
		genre.getMovies().add(this);
	}

	public void removeGenre(Genre genre) {
		this.genres.remove(genre);
		genre.getMovies().remove(this);
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	@Override
	public String toString() {
		String str = "";
		for (Genre genre : genres) {
			str += genre.getName() + " ";
		}
		return str;
	}

}
