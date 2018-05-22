package com.zheng.springboot.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@Component
public class MyErrorViewResolver implements ErrorViewResolver {
	@Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
										 HttpStatus status, Map<String, Object> model) {
        // Use the request or status to optionally return a ModelAndView
		ModelAndView mav = new ModelAndView("error/error", model);
		return mav;
	}

}