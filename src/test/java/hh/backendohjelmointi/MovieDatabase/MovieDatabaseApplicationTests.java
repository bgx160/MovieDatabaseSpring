package hh.backendohjelmointi.MovieDatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backendohjelmointi.MovieDatabase.domain.GenreRepository;
import hh.backendohjelmointi.MovieDatabase.domain.MovieRepository;
import hh.backendohjelmointi.MovieDatabase.domain.ReviewRepository;
import hh.backendohjelmointi.MovieDatabase.domain.UserRepository;

@SpringBootTest
class MovieDatabaseApplicationTests {
	
	@Autowired
	GenreRepository grepo;
	
	@Autowired
	MovieRepository mrepo;
	
	@Autowired
	ReviewRepository rrepo;
	
	@Autowired
	UserRepository urepo;
	

	@Test
	void contextLoads() {
		assertThat(grepo).isNotNull();
		assertThat(mrepo).isNotNull();
		assertThat(rrepo).isNotNull();
		assertThat(urepo).isNotNull();
	}

}
