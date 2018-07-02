package com.tarena.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.dao.RoleMapper;
import com.tarena.service.RoleService;

@Service("roleService")
public class RoleServiceipl implements RoleService {
	@Resource(name = "roleMapper")
	private RoleMapper RoleMapper;
}
