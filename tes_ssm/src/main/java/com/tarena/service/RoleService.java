package com.tarena.service;

import com.tarena.vo.Page;
import com.tarena.vo.Result;

public interface RoleService {
	/**
	 * 分页查询方法
	 * @param page
	 * @return
	 */
	public Result findRolesPage(Page page);
	//添加角色
	public Result addRole(String roleName);

}
