package com.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.dto.ResponseDto;
import com.web.dto.UserDto;
import com.web.service.UserService;
import com.web.utils.ConvertUtil;
import com.web.utils.HttpUtil;

@Controller
public class IndexController {
	@Resource
	private UserService userService;

	@RequestMapping(value = "/index")
	public String index() {
		return "index/index";
	}

	@RequestMapping(value = "/signin")
	public String signin() {
		return "index/login";
	}

	@RequestMapping(value = "/signup")
	public String signup() {
		return "index/register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto login(HttpServletRequest request) {
		UserDto userDto = ConvertUtil.requestToBean(UserDto.class, request);
		userDto.setIp(HttpUtil.getIpAddr(request));
		ResponseDto resp = this.userService.login(userDto);
		return resp;
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
