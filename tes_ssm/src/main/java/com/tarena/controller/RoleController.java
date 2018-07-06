package com.tarena.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tarena.entity.Role;
import com.tarena.entity.User;
import com.tarena.service.RoleService;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Controller
@RequestMapping("role/")
public class RoleController {
	@Resource(name="roleService")
	private RoleService roleService;
	@RequestMapping(value="findRolesByPage",method=RequestMethod.GET)
	@ResponseBody
	public Result findRoleByPage(Page page){
		Result result=null;
		result=this.roleService.findRolesPage(page);
		result.setStatus(1);
		return result;
	}
	@RequestMapping(value="addRole/{roleName}",method=RequestMethod.POST)
	@ResponseBody
	public Result addRole(@PathVariable("roleName") String roleName){
		Result result=null;
		result=this.roleService.addRole(roleName);
		System.out.println(result);
		return result;
	}
	@RequestMapping(value="updateRole",method=RequestMethod.POST)
	@ResponseBody
	public Result updateRole(Role role){
		Result result=null;
		result=this.roleService.updateRole(role);
		result.setStatus(1);
		System.out.println(role.getId()+" "+role.getName());
		return result;
	}
	@RequestMapping(value="deleteRole/{roleId}",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteRole(@PathVariable("roleId") String roleId){
		Result result=null;
		result=new Result();
		result=this.roleService.deleteRole(roleId);
		result.setStatus(1);
		System.out.println(3333);
		return result;
	}
	@RequestMapping(value="findAllRoles",method=RequestMethod.GET)
	@ResponseBody
	public Result findAllRoles(){
		Result result=null;
		result=this.roleService.findAllRoles();
		result.setStatus(1);
		System.out.println(3333);
		return result;
	}
}
