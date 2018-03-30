package com.lyu.csdndemo.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.OrgGroup;
import com.lyu.csdndemo.sys.entity.QueryOrgGroup;
import com.lyu.csdndemo.sys.dao.OrgGroupDao;

/**
 * @author lyu
 **/
@Service("orgGroupService")
@Transactional(rollbackFor = {IllegalArgumentException.class})
public class OrgGroupService extends GenericService<OrgGroup, QueryOrgGroup> {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrgGroupDao orgGroupDao;

    @Override
    protected GenericDao<OrgGroup, QueryOrgGroup> getDao() {
        return orgGroupDao;
    }

    /**
     * 根据父节点来查询最大的节点的值
     *
     * @param parentNode
     * @return
     */
    public String getMaxOrgGroup(String parentNode) {
        return orgGroupDao.getMaxOrgGroup(parentNode);
    }

    /**
     * 根据菜单节点NODE来查询节点数据
     *
     * @param node
     * @return
     */
    public OrgGroup findByNode(String node) {
        return orgGroupDao.findByNode(node);
    }
}