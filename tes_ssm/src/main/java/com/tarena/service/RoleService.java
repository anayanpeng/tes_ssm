package com.tarena.service;

import com.tarena.entity.Role;
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
	//更改角色
	public Result updateRole(Role role);
	//删除角色
	public Result deleteRole(String roleId);

}
