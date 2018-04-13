package com.lyu.tech.sys.service;

import com.lyu.tech.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.tech.common.base.service.GenericService;
import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.UserAssociateRole;
import com.lyu.tech.sys.entity.QueryUserAssociateRole;
import com.lyu.tech.sys.dao.UserAssociateRoleDao;

/**
 * @author lyu
 **/
@Service("userAssociateRoleService")
@Transactional(rollbackFor = {IllegalArgumentException.class})
public class UserAssociateRoleService extends GenericService<UserAssociateRole, QueryUserAssociateRole> {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserAssociateRoleDao userAssociateRoleDao;

    @Override
    protected GenericDao<UserAssociateRole, QueryUserAssociateRole> getDao() {
        return userAssociateRoleDao;
    }

    /**
     * 根据用户删除角色
     *
     * @param user
     * @return
     */
    public boolean removeUserRole(User user) {
        return userAssociateRoleDao.removeUserRole(user) > 0;
    }
}