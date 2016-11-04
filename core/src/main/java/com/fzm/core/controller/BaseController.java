package com.fzm.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fzm.base.domain.BaseEntity;

@Controller
@RequestMapping("/")
public class BaseController {

	@RequestMapping(value="",method=RequestMethod.GET)
	private ModelAndView  anyone(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = request.getServletPath();
		if(url!=null && url.length()>0){
			return new ModelAndView(url);
		}
		else{
			return new ModelAndView("index");
		}
	}
	
	
}
