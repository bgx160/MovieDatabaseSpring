package hh.backendohjelmointi.MovieDatabase.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backendohjelmointi.MovieDatabase.domain.MovieRepository;
import hh.backendohjelmointi.MovieDatabase.domain.Review;
import hh.backendohjelmointi.MovieDatabase.domain.ReviewRepository;
import hh.backendohjelmointi.MovieDatabase.domain.UserRepository;

@Controller
public class ReviewController {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/newreview/{id}")
	public String newReview(@PathVariable("id") Long id, Model m) {
		Review review = new Review();
		review.setMovie(movieRepository.findById(id).get());
		m.addAttribute("review", review);
		return "addreview";
	}

	@PostMapping("/savereview")
	public String saveReview(Review review, Principal principal) {
		review.setUser(userRepository.findByUsername(principal.getName()));
		reviewRepository.save(review);
		return "redirect:/movielist";
	}

	@GetMapping("/reviews/{id}")
	public String movieReviews(@PathVariable("id") Long movieId, Model m) {
		m.addAttribute("reviews", reviewRepository.findByMovieMovieId(movieId));
		return "reviews";
	}
	
	@GetMapping("/review/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReview(@PathVariable("id") Long id) {
		reviewRepository.deleteById(id);
		return "redirect:/movielist";
	}

}
