package com.fzm.core.controller;

import java.util.List;

import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fzm.core.domain.User;
import com.fzm.core.dto.ResultMapDto;
import com.fzm.core.service.UserService;

@Controller
@RequestMapping("/user")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	private List<User> list(){
		List<User> list = this.userService.getList();
		return list;
	}
	

	@RequestMapping(value="/list",method=RequestMethod.GET)
	private ModelAndView listPage(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/list");
		List<User> list = this.userService.getList();
		mv.addObject("users", list);
		return mv;
	}
	
	
}
