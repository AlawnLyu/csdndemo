package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.entity.QueryTree;
import com.lyu.csdndemo.sys.service.TreeService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/tree")
public class TreeController extends GenericController<Tree, QueryTree> {
	@Inject
	private TreeService treeService;
	@Override
	protected GenericService<Tree, QueryTree> getService() {
		return treeService;
	}
}