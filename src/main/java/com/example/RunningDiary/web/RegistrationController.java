package com.example.RunningDiary.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.RunningDiary.domain.SignupForm;
import com.example.RunningDiary.domain.User;
import com.example.RunningDiary.domain.UserRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserRepository urepository;
	
	
	
		@RequestMapping(value = "registration")
		public String addUser(Model model) {
			model.addAttribute("signupform", new SignupForm());
			return "registration";
		}
		//create user
		@RequestMapping(value = "saveuser", method = RequestMethod.POST)
		public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
			if (!bindingResult.hasErrors()) {
				//checks that password and password check match with each other
				if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
					String pswd = signupForm.getPassword();
					BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
					String hashPswd = bc.encode(pswd);

					User newUser = new User();
					newUser.setPasswordHash(hashPswd);
					newUser.setUsername(signupForm.getUsername());
					newUser.setRole("USER");
					// check to see if there is already a user with that username
					if (urepository.findByUsername(signupForm.getUsername()) == null) {
						urepository.save(newUser);
					} else {
						bindingResult.rejectValue("username", "error.userexists", "Username already exists");
						return "registration";
					}
					//check that passwords match
				} else {
					bindingResult.rejectValue("passwordCheck", "error.passMatch", "Passwords do not match");
					return "registration";
				}
			} else {
				return "registration";
			}
			return "redirect:/login";
		}
}
