package org.cch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	public IndexController() {
		super();
		System.out.println("IndexController...");
	}

	@RequestMapping("/hello")
	public ModelAndView hello() {
		System.out.println("hello");
		int i = 1 / 0;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		mav.addObject("msg", "hello kitty");
		return mav;
	}

}
