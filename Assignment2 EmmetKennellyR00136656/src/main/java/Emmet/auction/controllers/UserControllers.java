package Emmet.auction.controllers;

import java.security.Principal;
import java.util.List;

import Emmet.auction.EmmetProjectApplication;
import Emmet.auction.domain.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Emmet.auction.domain.JobForm;
import Emmet.auction.domain.User;
import Emmet.auction.domain.UserForm;
import Emmet.auction.service.JobService;
import Emmet.auction.service.UserService;

@Controller
public class UserControllers {

	@Autowired
    private UserService userService;


	@GetMapping("/")
	public String homepage()
	{
		return "index";
	}

	@GetMapping(value = "/registration")
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());

	        return "Registration";
	    }
	   @PostMapping(value = "/registration")
	    public String registration(@ModelAttribute("userForm") UserForm postedUserForm, Model model) {
//	       (userForm.getUsername(), userForm.getPassword());
	        User newUser = new User(
	        		postedUserForm.getName(),
	        		postedUserForm.getEmail(),
	        		postedUserForm.getPassword(),
	        		postedUserForm.getPhoneNumber(), EmmetProjectApplication.STANDARD_USER_ROLE);
		   userService.saveUser(newUser);
		   return "index";

	    }

}
