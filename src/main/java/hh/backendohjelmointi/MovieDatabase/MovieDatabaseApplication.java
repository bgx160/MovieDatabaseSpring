package hh.backendohjelmointi.MovieDatabase;






import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backendohjelmointi.MovieDatabase.domain.Genre;
import hh.backendohjelmointi.MovieDatabase.domain.GenreRepository;
import hh.backendohjelmointi.MovieDatabase.domain.Movie;
import hh.backendohjelmointi.MovieDatabase.domain.MovieRepository;
import hh.backendohjelmointi.MovieDatabase.domain.Review;
import hh.backendohjelmointi.MovieDatabase.domain.ReviewRepository;
import hh.backendohjelmointi.MovieDatabase.domain.User;
import hh.backendohjelmointi.MovieDatabase.domain.UserRepository;

@SpringBootApplication
public class MovieDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDatabaseApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(MovieRepository movieRepository, GenreRepository genreRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
		return (args) -> {
			Genre genre1 = genreRepository.save(new Genre("Animation"));
			Genre genre2 = genreRepository.save(new Genre("Adventure"));
			Genre genre3 = genreRepository.save(new Genre("Drama"));
			Genre genre4 = genreRepository.save(new Genre("Action"));
			Genre genre5 = genreRepository.save(new Genre("Horror"));
			Genre genre6 = genreRepository.save(new Genre("Mystery"));
			
			Movie movie1 = new Movie("The Lion King", "Roger Allers", 1994);
			Movie movie2 = new Movie("Toystory", "John Lasseter", 1995);
			Movie movie3 = new Movie("Matrix", "Lana Wachowski", 1999);
			Movie movie4 = new Movie("The Cabin in the Woods", "Drew Goddard", 2011);
			Movie movie5 = new Movie("Trainspotting", "Danny Boyle", 1996);
	
			movie1.addGenre(genre1);
			movie1.addGenre(genre2);
			movie1.addGenre(genre3);
			movie2.addGenre(genre1);
			movie3.addGenre(genre4);
			movie4.addGenre(genre5);
			movie4.addGenre(genre6);
			movie5.addGenre(genre3);
			
			movieRepository.save(movie1);
			movieRepository.save(movie2);
			movieRepository.save(movie3);
			movieRepository.save(movie4);
			movieRepository.save(movie5);

			User user1 = userRepository.save(new User("John", "$2a$10$.bJ/lSR7RT8i1q/MME7lAegpPfPRejkfyt6CyyTqnUwmaJQqy5eMS", "USER"));
			User user2 = userRepository.save(new User("Lucy", "$2a$10$p2.8cwqUzqJrceRa7fUcI.JwM3bnxhzG12w9y8D.t4p25AYGRY1pi", "USER"));
			User user3 = userRepository.save(new User("user3", "$2a$10$70X/LTHA/E9.lMLDEBhUu.PtN8aDe7ZWnfCEfx7SGZXYaDXYy/Dt6", "USER"));
			User admin = userRepository.save(new User("admin", "$2a$10$qak17yfvqCAL68T0rbxBo.jts8AqiZfJPp2dnni4A9B2XdFQ.eZNu", "ADMIN"));
		
			reviewRepository.save(new Review(5, "Very good!", movie1, user1));
			reviewRepository.save(new Review(5, "The classic!", movie1, user2));
			reviewRepository.save(new Review(4, "Great soundtrack!", movie1, user3));
			reviewRepository.save(new Review(5, "Masterpiece", movie2, user2));
			reviewRepository.save(new Review(1, "Waste of time!", movie4, user3));
			reviewRepository.save(new Review(5, "The best horror movie ever made", movie4, user1));
			reviewRepository.save(new Review(5, "My personal favourite", movie5, user2));
			reviewRepository.save(new Review(3, "Not good not bad", movie3, admin));

		};
	}

}



