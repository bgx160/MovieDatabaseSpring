package hh.backendohjelmointi.MovieDatabase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reviewId;

	@ManyToOne
	@JoinColumn(name = "movieId", nullable = false)
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Min(1)
	@Max(5)
	@NotNull
	private int rating;
	private String comment;

	public Review() {
		super();
	}

	public Review(int rating, String comment, Movie movie, User user) {
		super();
		this.user = user;
		this.rating = rating;
		this.comment = comment;
		this.movie = movie;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public Movie getMovie() {
		return movie;
	}

	public int getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setComment(String review) {
		this.comment = review;
	}

}
