package com.codingdojo.loginandregistration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.loginandregistration.models.LoginUser;
import com.codingdojo.loginandregistration.models.User;
import com.codingdojo.loginandregistration.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userServ;

//	DONE

	@GetMapping("/")
	public String rPage(@ModelAttribute("newUser") User newUser, @ModelAttribute("newLogin") LoginUser newLogin) {
		return "loginAndRegistratin.jsp";
	}

//	DONE

	@GetMapping("/dashboard")
	public String rDPage(HttpSession session, Model model) {

		if (session.getAttribute("userID") == null) {
			return "redirect:/";
		}

		User thisUser = this.userServ.findUserById((Long) session.getAttribute("userID"));
		model.addAttribute("user", thisUser);
		return "dashboard.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		User thisUser = this.userServ.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			System.out.println("ERROR");
			return "loginAndRegistratin.jsp";
		}

		session.setAttribute("userID", thisUser.getId());
		System.out.println("Success");

		return "redirect:/dashboard";
	}  

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User thisUser = userServ.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			System.out.println("ERROR");
			return "loginAndRegistratin.jsp";
		}

		session.setAttribute("userID", thisUser.getId());
		System.out.println("Success");

		return "redirect:/dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
