package com.lyu.tech.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.tech.common.base.service.GenericService;
import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.Dict;
import com.lyu.tech.sys.entity.QueryDict;
import com.lyu.tech.sys.dao.DictDao;

/**
 *@author lyu
 **/
@Service("dictService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class DictService extends GenericService<Dict, QueryDict> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private DictDao dictDao;
	@Override
	protected GenericDao<Dict, QueryDict> getDao() {
		return dictDao;
	}
}