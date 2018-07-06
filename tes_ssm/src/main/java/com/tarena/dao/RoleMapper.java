package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Role;
import com.tarena.entity.User;
import com.tarena.vo.Page;

public interface RoleMapper {
	/**
	 * 分页查询角色信息
	 * 带有模糊条件
	 * @param page
	 * @return
	 */
	public int getCount(Page page);

	public List<Role> getRolesByPage(Page page);
	//添加角色
	public int addRole(Role role);
	//更新角色
	public void updateRole(Role role);
	//删除角色
	public void deleteRole(String roleId);

	public List<Role> findAllRoles();

}
