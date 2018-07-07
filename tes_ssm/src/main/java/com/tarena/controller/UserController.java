package com.tarena.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@RequestMapping(value="newUser",method=RequestMethod.POST)
	public void addUser(User user,String roleId,MultipartFile addPicture, HttpServletRequest request,HttpServletResponse response){
	System.out.println("addUser()-->"+addPicture);	
	this.userService.addUser(user,roleId,addPicture,request,response);
	}
	@RequestMapping(value="exportExcel",method=RequestMethod.GET)
	public void export_user(HttpServletRequest request,HttpServletResponse response){
		byte[]data=this.userService.export_User();
		if(data!=null){
			//把字节数组下载到客户端
			try {
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment;fileName=alluser.xls");
				response.setContentLength(data.length);
				OutputStream os;
				os = response.getOutputStream();
				os.write(data);
				os.flush();
				os.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
