package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.RoleAssociateTree;
import com.lyu.csdndemo.sys.entity.QueryRoleAssociateTree;
import com.lyu.csdndemo.sys.service.RoleAssociateTreeService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/roleAssociateTree")
public class RoleAssociateTreeController extends GenericController<RoleAssociateTree, QueryRoleAssociateTree> {
	@Inject
	private RoleAssociateTreeService roleAssociateTreeService;
	@Override
	protected GenericService<RoleAssociateTree, QueryRoleAssociateTree> getService() {
		return roleAssociateTreeService;
	}
}