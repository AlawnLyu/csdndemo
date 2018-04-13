package com.lyu.tech.sys.dao;

import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.OrgGroup;
import com.lyu.tech.sys.entity.QueryOrgGroup;

/**
 * @author lyu
 **/
public interface OrgGroupDao extends GenericDao<OrgGroup, QueryOrgGroup> {

    /**
     * 根据父节点来查询最大的节点的值
     *
     * @param parentNode
     * @return
     */
    String getMaxOrgGroup(String parentNode);

    /**
     * 根据菜单节点NODE来查询节点数据
     *
     * @param node
     * @return
     */
    OrgGroup findByNode(String node);
}