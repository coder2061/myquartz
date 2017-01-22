package com.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.core.RedisManager;
import com.web.dto.ResponseDto;
import com.web.dto.UserDto;
import com.web.service.UserService;
import com.web.utils.PropertiesUtil;
import com.web.utils.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static final long EXPIRE_TIME = 3600L;
	@Resource
	private RedisManager redisManager;

	@Override
	public ResponseDto login(UserDto userDto) {
		return this.sso(userDto.getAccount(), userDto.getPassword(),
				userDto.getIp());
	}

	/**
	 * 单点登录（Single Sign On），简称为 SSO，目前比较流行的企业业务整合的解决方案之一。
	 * 指在多个应用系统中，用户只需要登录一次就可以访问所有相互信任的应用系统。
	 * 
	 * @param account
	 * @param password
	 * @return ResponseDto
	 */
	private ResponseDto sso(String account, String password, String ip) {
		ResponseDto resp = new ResponseDto();
		// 校验登录信息
		if (StringUtil.isBlank(account) || StringUtil.isBlank(password)
				|| StringUtil.isBlank(ip)) {
			resp.setCode("1");
			resp.setMsg("用户登录信息不完整");
			return resp;
		}

		// 获取登录账户对应用户信息
		Map<String, String> userInfo = PropertiesUtil
				.propertiesToMap("account.properties");
		// 判断用户是否存在
		if (userInfo == null || userInfo.isEmpty()
				|| !account.equals(userInfo.get("username"))) {
			resp.setCode("2");
			resp.setMsg("用户登录信息有误");
			return resp;
		}

		String userid = userInfo.get("userid");
		String username = userInfo.get("username");
//		System.out.println(account + "--------" + userid + "--------" + username + "--------" + password + "--------" + ip);
		// 获取用户登录信息
		Map<Object, Object> userCache = redisManager.getCacheMap(userid);
		String token = null;
		// 判断是否已登录
		if (userCache != null && !userCache.isEmpty()) {
			// 判断已登录IP地址是否是当前IP地址
			if (!ip.equals(userCache.get("ip"))) {
				resp.setCode("3");
				resp.setMsg("用户已在别处登录，请先下线再重新登录");
				return resp;
			} else {
				token = StringUtil.CreateNoncestr();
				userCache.put("ip", ip);
				userCache.put("token", token);
				redisManager.setCacheMap(userid, userCache);
				redisManager.expire(userid, EXPIRE_TIME, 3);
			}
		} else {
			if (password.equals(userInfo.get("password"))) {
				token = StringUtil.CreateNoncestr();
				userCache = new HashMap<Object, Object>();
				userCache.put("ip", ip);
				userCache.put("userid", userid);
				userCache.put("username", username);
				userCache.put("token", token);
				redisManager.setCacheMap(userid, userCache);
				redisManager.expire(userid, EXPIRE_TIME, 3);
			} else {
				resp.setCode("4");
				resp.setMsg("用户登录密码有误");
			}
		}

		if (token != null) {
			resp.setCode("0");
			resp.setMsg("用户登录成功");
			resp.setToken(token);
		} else {
			resp.setCode("-1");
			resp.setMsg("用户登录失败");
		}
		return resp;
	}
	
	@Override
	public void lock() {
		
	}
	
	@Override
	public void unlock() {
		
	}
	
	@Override
	public void logout() {
		redisManager.remove("123456");
	}

	@Override
	public ResponseDto register(UserDto userDto) {
		return null;
	}

}
