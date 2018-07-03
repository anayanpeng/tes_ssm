package com.tarena.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.RoleMapper;
import com.tarena.dao.UserMapper;
import com.tarena.entity.User;
import com.tarena.service.UserService;
import com.tarena.util.PageUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;
	@Resource(name="pageUtil")
	private PageUtil pageUtil;
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

	@Override
	public Result findUsersByPage(Page page) {
		Result result=new Result();
		//处理角色类型
		String roleType=page.getRoleType();
		if("all".equals(roleType)){
			page.setRoleKeyword("%%");
		}else{
			page.setRoleKeyword("%"+page.getRoleType()+"%");
		}
		page.setPageSize(pageUtil.getPageSize());
		String userKw=page.getUserKeyword().equals("undefinded")?"%%":"%"+page.getUserKeyword()+"%";
		page.setUserKeyword(userKw);
		//获取总记录数
		int totalCount=this.userMapper.getCount();
		page.setTotalCount(totalCount);
		//计算总页数
		int totalPage=(totalCount%page.getPageSize()==0)?(totalCount/page.getPageSize()):(totalCount/page.getPageSize()+1);
		page.setTotalPage(totalPage);
		//计算前一页
		if(page.getCurrentPage()==1){
			page.setPreviousPage(1);
		}else{
			page.setPreviousPage(page.getCurrentPage()-1);
		}
		//计算后一页
		if(page.getCurrentPage()==totalPage){
			page.setNextPage(totalPage);
		}else{
			page.setNextPage(page.getCurrentPage()+1);
		}
		//获取当前页的数据
		List<User> users=this.userMapper.findUsersByPage(page);
		page.setData(users);
		//计算分页条上有多少超链接
		page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
		
		page.setData(this.userMapper.findUsersByPage(page));
		System.out.println(page);
		result.setStatus(1);
	    result.setData(page);
		return result;
	}
}
