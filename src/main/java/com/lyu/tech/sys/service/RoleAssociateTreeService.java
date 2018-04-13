package com.lyu.tech.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.tech.common.base.service.GenericService;
import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.RoleAssociateTree;
import com.lyu.tech.sys.entity.QueryRoleAssociateTree;
import com.lyu.tech.sys.dao.RoleAssociateTreeDao;

/**
 *@author lyu
 **/
@Service("roleAssociateTreeService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class RoleAssociateTreeService extends GenericService<RoleAssociateTree, QueryRoleAssociateTree> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private RoleAssociateTreeDao roleAssociateTreeDao;
	@Override
	protected GenericDao<RoleAssociateTree, QueryRoleAssociateTree> getDao() {
		return roleAssociateTreeDao;
	}
}