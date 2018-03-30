package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.OrgGroup;
import com.lyu.csdndemo.sys.entity.QueryOrgGroup;

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