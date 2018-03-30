package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.UserAssociateRole;
import com.lyu.csdndemo.sys.entity.QueryUserAssociateRole;
import com.lyu.csdndemo.sys.service.UserAssociateRoleService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/userAssociateRole")
public class UserAssociateRoleController extends GenericController<UserAssociateRole, QueryUserAssociateRole> {
	@Inject
	private UserAssociateRoleService userAssociateRoleService;
	@Override
	protected GenericService<UserAssociateRole, QueryUserAssociateRole> getService() {
		return userAssociateRoleService;
	}
}