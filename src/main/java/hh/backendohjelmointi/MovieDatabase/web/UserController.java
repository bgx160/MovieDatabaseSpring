package hh.backendohjelmointi.MovieDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backendohjelmointi.MovieDatabase.domain.SignupForm;
import hh.backendohjelmointi.MovieDatabase.domain.User;
import hh.backendohjelmointi.MovieDatabase.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/userlist")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String userList(Model m) {
		m.addAttribute("users", userRepository.findAll());
		return "userlist";
	}

	@GetMapping("/userlist/search")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String searchUsers(Model m, @Param("keyword") String keyword) {
		m.addAttribute("users", userRepository.findByUsernameContainingIgnoreCase(keyword));
		return "userlist";
	}

	@GetMapping("/user/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String setAdmin(@PathVariable("id") Long userId, Model m) {
		m.addAttribute("user", userRepository.findById(userId).get());
		return "edituser";
	}

	@GetMapping("/user/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable("id") Long userId) {
		userRepository.delete(userRepository.findById(userId).get());
		return "redirect:/userlist";
	}

	@PostMapping("/user/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveEditedUser(User user) {
		userRepository.save(user);
		return "redirect:/userlist";
	}

	@GetMapping("/signup")
	public String newUser(Model m) {
		m.addAttribute("signup", new SignupForm());
		return "signup";
	}

	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("signup") SignupForm signupForm, BindingResult result) {
		if (!result.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

				String hashPassword = bc.encode(signupForm.getPassword());

				User newUser = new User(signupForm.getUsername(), hashPassword, signupForm.getRole());

				if (userRepository.findByUsername(signupForm.getUsername()) == null) {
					userRepository.save(newUser);
				} else {
					result.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				result.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

}
