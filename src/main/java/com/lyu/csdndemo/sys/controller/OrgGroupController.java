package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.OrgGroup;
import com.lyu.csdndemo.sys.entity.QueryOrgGroup;
import com.lyu.csdndemo.sys.service.OrgGroupService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/orgGroup")
public class OrgGroupController extends GenericController<OrgGroup, QueryOrgGroup> {
	@Inject
	private OrgGroupService orgGroupService;
	@Override
	protected GenericService<OrgGroup, QueryOrgGroup> getService() {
		return orgGroupService;
	}
}