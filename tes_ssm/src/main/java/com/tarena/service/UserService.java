package com.tarena.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

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

	public void addUser(User user, String roleId, MultipartFile addPicture, HttpServletRequest request,
			HttpServletResponse response);
	/**
	 * 导出excel的业务方法
	 * @return 
	 */
	public byte[] export_User();
	public Result login_shiro(String loginName, String password);
}
