package com.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.dto.ResponseDto;
import com.web.dto.UserDto;
import com.web.service.UserService;
import com.web.utils.ConvertUtil;
import com.web.utils.HttpUtil;

@Controller
public class IndexController {
	@Resource
	private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index/index";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() {
		return "index/login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "index/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserDto userDto = ConvertUtil.requestToBean(UserDto.class, request);
		userDto.setIp(HttpUtil.getIpAddr(request));
		ResponseDto resp = this.userService.login(userDto);
		if ("0".equals(resp.getCode())) {
			mav.setViewName("index/index");
		} else {
			mav.addObject(resp);
			mav.setViewName("index/login");
		}
		return mav;
	}
	
	@RequestMapping(value = "/lock", method = RequestMethod.GET)
	public String lock(HttpServletRequest request) {
		this.userService.lock();
		return "index/lock_screen";
	}
	
	
	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	public String unlock(HttpServletRequest request) {
		this.userService.unlock();
		return "index/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		this.userService.logout();
		return "index/login";
	}

	@RequestMapping(value = "/register")
	@ResponseBody
	public ResponseDto register(HttpServletRequest request) {
		UserDto userDto = ConvertUtil.requestToBean(UserDto.class, request);
		userDto.setIp(HttpUtil.getIpAddr(request));
		ResponseDto resp = this.userService.register(userDto);
		return resp;
	}

}
