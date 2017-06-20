package com.insightkorea.korea.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController{
	
	@RequestMapping("/")
	public ModelAndView root() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/home");
		return model;
	}

//	@RequestMapping("/home")
//	public ModelAndView home() {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("/home");
//		return model;
//	}

	@RequestMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@RequestMapping("/user")
	public String user() {
		return "/user";
	}

	@RequestMapping("/about")
	public String about() {
		return "/about";
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/login");
		return model;
	}

	@RequestMapping("/403")
	public String error403() {
		return "/error/403";
	}

}
