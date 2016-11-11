package com.fzm.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fzm.core.domain.User;
import com.fzm.core.dto.ResultMapDto;
import com.fzm.base.dto.ResultPage;
import com.fzm.core.service.UserService;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
@RequestMapping("/user")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	private ResultPage<User> list(ServletRequest request, Integer pageIndex, Integer pageSize, 
			Integer sex, @RequestParam(value = "startDate", required = false) Date startDate, 
			@RequestParam(value = "endDate", required = false) Date endDate, String name){
		Map<String,Object> conditions = new HashMap<String,Object>();
		if(sex!=null){
			conditions.put("EQ_sex", sex);
		}
		if(startDate!=null){
			conditions.put("GE_birthday", startDate);
		}
		if(endDate!=null){
			conditions.put("LE_birthday", endDate);
		}
		if(name!=null){
			conditions.put("LIKE_name", name);
		}
		ResultPage<User> resultPage = this.userService.getPageInfo(pageIndex, pageSize, conditions);
		return resultPage;
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	private ResultPage<User> getList(ServletRequest request, Integer pageIndex, Integer pageSize,
		Integer sex, Date startDate, Date endDate, String name){
			Map<String,Object> conditions = new HashMap<String,Object>();
			if(sex!=null){
				conditions.put("sex", sex);
			}
			if(startDate!=null){
				conditions.put("startDate", startDate);
			}
			if(endDate!=null){
				conditions.put("endDate", endDate);
			}
			if(name!=null){
				conditions.put("name", name);
			}
			ResultPage<User> resultPage = this.userService.getPageInfo(pageIndex, pageSize, conditions);
			return resultPage;
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	private ResultMapDto save(ServletRequest request, User entity){
		ResultMapDto result = new ResultMapDto();
		if(this.userService.save(entity)){
			result.setSuccess(true);
		}
		else{
			result.setSuccess(false);
		}
		return result;
	}
	@RequestMapping(value="/get",method=RequestMethod.POST)
	@ResponseBody
	private ResultMapDto get(ServletRequest request, Long id){
		ResultMapDto result = new ResultMapDto();
		User entity = this.userService.get(id);
		if(entity!=null){
			result.setSuccess(true);
			result.setEntity(entity);
		}
		else{
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping(value="/image",method=RequestMethod.GET)
	@ResponseBody
	private void image(ServletRequest request, ServletResponse response){

		OutputStream os;
		try {
			os = response.getOutputStream();
			File f = new File("/static/img/1.jpg");
			System.out.println(f.exists());
			Thumbnails.of("E:\\java\\1.jpg").size(200, 200).toOutputStream(os);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/imageList",method=RequestMethod.GET)
	@ResponseBody
	private List<String> imageList(ServletRequest request, ServletResponse response){

		List<String> list = new ArrayList<String>();
		list.add("http://localhost:8080/static/img/1.jpg");
		list.add("http://localhost:8080/static/img/2.jpg");
		return list;
	}
	
}
