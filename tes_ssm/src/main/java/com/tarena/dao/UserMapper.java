package com.tarena.dao;

import java.util.List;

import com.tarena.entity.User;
import com.tarena.entity.UserRole;
import com.tarena.vo.Page;

public interface UserMapper {

	public String login(User user);
	/**
	 * 分页查询
	 * @return
	 */
	//获取总个数
	public int getCount(Page page);
	//添加用户
	public void addUser(User user);
	//分页(全部用户)
	public void addUserRole(UserRole ur);
	public List getUsers(Page page);
	//分页(指定具体角色）
	public int getCountByRole(Page page);
	public List getUsersByRole(Page page);

}
