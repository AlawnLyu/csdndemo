package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.UserRole;
import com.lyu.csdndemo.sys.entity.QueryUserRole;
import com.lyu.csdndemo.sys.service.UserRoleService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/userRole")
public class UserRoleController extends GenericController<UserRole, QueryUserRole> {
	@Inject
	private UserRoleService userRoleService;
	@Override
	protected GenericService<UserRole, QueryUserRole> getService() {
		return userRoleService;
	}
}