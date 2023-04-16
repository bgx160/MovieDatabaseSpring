package hh.backendohjelmointi.MovieDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import hh.backendohjelmointi.MovieDatabase.web.UserDetailServiceImpl;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;  




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
        .requestMatchers("/index", "/css/**", "/signup", "/saveuser").permitAll()
        .requestMatchers(toH2Console()).permitAll()
        .and().csrf().ignoringRequestMatchers(toH2Console())
        .and().formLogin().defaultSuccessUrl("/index", true)
        .and().httpBasic().and().authorizeHttpRequests().anyRequest().authenticated().and().formLogin().permitAll().and().logout()
				.permitAll().and().httpBasic();
		http.headers().frameOptions().disable();
		return http.build();
	}
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}}
