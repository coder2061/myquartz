package com.web.service;

import com.web.dto.ResponseDto;
import com.web.dto.UserDto;

public interface UserService {
	ResponseDto login(UserDto userDto);

	ResponseDto register(UserDto userDto);

}
