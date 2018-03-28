package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.Dict;
import com.lyu.csdndemo.sys.entity.QueryDict;
import com.lyu.csdndemo.sys.service.DictService;

/**
 *@author lyu
 **/
@Controller
@RequestMapping("/dict")
public class DictController extends GenericController<Dict, QueryDict> {
	@Inject
	private DictService dictService;
	@Override
	protected GenericService<Dict, QueryDict> getService() {
		return dictService;
	}
}