package org.cch.controller;

import javax.servlet.http.HttpServletRequest;

import org.cch.component.common.exceptions.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rest")
public class MyRestController {

	@RequestMapping
	public String welcome(HttpServletRequest request) throws Exception {
		String act = request.getParameter("act");
		if ("1".equals(act)) {
			throw new MyException("my exception");
		}
		if ("2".equals(act)) {
			throw new Exception("exception");
		}
		return "welcome";
	}

	@RequestMapping("/json")
	public String json(HttpServletRequest request) throws Exception {
		String act = request.getParameter("act");
		if ("1".equals(act)) {
			throw new MyException("my exception");
		}
		if ("2".equals(act)) {
			throw new Exception("exception");
		}
		return "{}";
	}
}
