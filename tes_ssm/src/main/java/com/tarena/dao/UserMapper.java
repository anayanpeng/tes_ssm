package com.tarena.dao;

import java.util.List;

import com.tarena.entity.User;
import com.tarena.vo.Page;

public interface UserMapper {

	public String login(User user);
	/**
	 * 分页查询
	 * @return
	 */
	public int getCount();
	
	public List findUsersByPage(Page page);

}
