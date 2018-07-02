package com.tarena.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.RoleMapper;
import com.tarena.dao.UserMapper;
import com.tarena.entity.User;
import com.tarena.service.UserService;
import com.tarena.vo.Result;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;

	@Override
	public Result login(String loginName, String password) {
		Result result=new Result();
		User user=new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		String userId=this.userMapper.login(user);
		if(userId!=null){
			result.setStatus(1);
			result.setMessage("登录成功!");
		}else{
			result.setStatus(0);
			result.setMessage("登录失败!");
		}
		return result;
	}
}
