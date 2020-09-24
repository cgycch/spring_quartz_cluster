package org.cch.controller;

import javax.servlet.http.HttpServletRequest;

import org.cch.component.common.exceptions.MyException;
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
	public ModelAndView hello(HttpServletRequest request) throws Exception {
		System.out.println("hello");
		String act = request.getParameter("act");
		if ("1".equals(act)) {
			throw new MyException("my exception");
		}
		if ("2".equals(act)) {
			throw new Exception("exception");
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		mav.addObject("msg", "hello kitty");
		return mav;
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest request) throws Exception {
		System.out.println("test");
		String act = request.getParameter("act");
		if ("1".equals(act)) {
			throw new MyException("my exception");
		}
		if ("2".equals(act)) {
			throw new Exception("exception");
		}
		return "welcome";
	}
}
