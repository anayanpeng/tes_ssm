package com.tarena.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.RoleMapper;
import com.tarena.entity.Role;
import com.tarena.entity.User;
import com.tarena.service.RoleService;
import com.tarena.util.PageUtil;
import com.tarena.util.UUIDUtil;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Service("roleService")
public class RoleServiceipl implements RoleService {
	@Resource(name = "roleMapper")
	private RoleMapper RoleMapper;
	@Resource(name="pageUtil")
	private PageUtil pageUtil;

	@Override
	public Result findRolesPage(Page page) {
		Result result=new Result();
		String roleKw=page.getRoleKeyword().equals("undefined")?"%%":"%"+page.getRoleKeyword()+"%";
		System.out.println(roleKw);
		page.setRoleKeyword(roleKw);
		page.setPageSize(this.pageUtil.getPageSize());
		//获取总记录数
		int totalCount=this.RoleMapper.getCount(page);
		page.setTotalCount(totalCount);
		//计算总页数
		int totalPage=(totalCount%page.getPageSize()==0)?(totalCount/page.getPageSize()):(totalCount/page.getPageSize())+1;
		page.setTotalCount(totalCount);
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
		//获取当前页数据
		List<Role> role=this.RoleMapper.getRolesByPage(page);
		page.setData(role);
		//获取分页组建中超链接的个数
		page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
		System.out.println(page);
		result.setStatus(1);
		result.setData(page);
		return result;
	}
	//增加角色
	@Override
	public Result addRole(String roleName) {
		Result result=new Result();
		Role role=new Role();
		role.setId(UUIDUtil.getUUID());
		role.setName(roleName);
		int rowAffect=this.RoleMapper.addRole(role);
		result.setStatus(1);
		result.setMessage("添加角色成功");
		return result;
	}
	//更新角色
	@Override
	public Result updateRole(Role role) {
		Result result=new Result();
		this.RoleMapper.updateRole(role);
		result.setStatus(1);
		result.setMessage("更新成功");
		return result;
	}
	//删除角色
	@Override
	public Result deleteRole(String roleId) {
		Result result=new Result();
		this.RoleMapper.deleteRole(roleId);
		result.setStatus(1);
		result.setMessage("删除成功");
		return result;
	}
	//添加所有角色信息
	@Override
	public Result findAllRoles() {
		Result result=new Result();
		List<Role> roles=this.RoleMapper.findAllRoles();
		if(roles!=null){
			result.setStatus(1);
			result.setData(roles);
		}else{
			result.setStatus(0);
			result.setMessage("没有");
		}
		return result;
	}
}
