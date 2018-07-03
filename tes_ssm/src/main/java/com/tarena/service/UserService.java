package com.tarena.service;

import com.tarena.entity.User;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface UserService {
	/**
	 * 登陆的业务方法
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Result login(String loginName, String password);

	public Result findUsersByPage(Page page);

}
