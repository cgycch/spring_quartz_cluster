package org.cch.component.common.handler;

import org.cch.component.common.exceptions.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView customException(Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", e.getMessage());
		mv.setViewName("error");
		return mv;
	}

	@ExceptionHandler(MyException.class)
	public ModelAndView myException(MyException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", e.getMessage());
		mv.setViewName("error");
		return mv;
	}
}
