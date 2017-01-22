package com.web.service;

import com.web.dto.ResponseDto;
import com.web.dto.UserDto;

public interface UserService {
	ResponseDto login(UserDto userDto);

	void lock();
	
	void unlock();

	void logout();

	ResponseDto register(UserDto userDto);

}
