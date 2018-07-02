package com.tarena.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.service.RoleService;

@Controller
@RequestMapping("role/")
public class RoleController {
	@Resource(name="roleService")
	private RoleService roleService;
}
