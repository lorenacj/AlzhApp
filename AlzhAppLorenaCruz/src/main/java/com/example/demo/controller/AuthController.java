package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Carer;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private static final String LOGIN_VIEW = "login";
	private static final String REGISTER_VIEW = "register";
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error, @RequestParam(name = "logout", required=false) String logout, HttpServletRequest request) {
		model.addAttribute("carer", new Carer());
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);
		if (logout != null) {
			model.addAttribute("logoutMessage", "¡Has cerrado sesión correctamente!");
		}
		
		return LOGIN_VIEW;
	}
	
}
