package com.tarena.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.vo.Result;
@Controller
@RequestMapping("main/")
public class MainController {
	@RequestMapping(value="logout",method=RequestMethod.GET)
	@ResponseBody
 public Result logout(HttpSession session){
	 Result result=null;
		session.invalidate();//清楚session中所有的数据
		result.setStatus(1);
	return result;
 }
}
