package com.tarena.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.User;
import com.tarena.service.UserService;
import com.tarena.vo.Page;
import com.tarena.vo.Result;

@Controller
@RequestMapping("user/")
public class UserController {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value="login/{loginName}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Result login(@PathVariable("loginName") String loginName, @PathVariable("password") String password,
			HttpSession session) {
		System.out.println(111);
		System.out.println(loginName+" "+password);
		Result result = null;
		result = this.userService.login(loginName, password);
		System.out.println(123);
		return result;
	}
	@RequestMapping(value="findUsersByPage", method = RequestMethod.POST)
	@ResponseBody
	public Result login(Page page) {
		Result result=new Result();
		System.out.println(page.getCurrentPage()+" "+page.getUserKeyword()+" "+page.getRoleType());
		result=this.userService.findUsersByPage(page);
		return result;
	}
}
