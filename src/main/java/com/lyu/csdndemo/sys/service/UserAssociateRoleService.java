package com.lyu.csdndemo.sys.service;

import com.lyu.csdndemo.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.UserAssociateRole;
import com.lyu.csdndemo.sys.entity.QueryUserAssociateRole;
import com.lyu.csdndemo.sys.dao.UserAssociateRoleDao;

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