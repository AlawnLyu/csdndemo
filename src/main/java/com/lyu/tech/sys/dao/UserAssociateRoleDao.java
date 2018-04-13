package com.lyu.tech.sys.dao;

import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.User;
import com.lyu.tech.sys.entity.UserAssociateRole;
import com.lyu.tech.sys.entity.QueryUserAssociateRole;

/**
 * @author lyu
 **/
public interface UserAssociateRoleDao extends GenericDao<UserAssociateRole, QueryUserAssociateRole> {

    /**
     * 根据用户id,删除用户权限数据
     *
     * @param user
     * @return
     */
    int removeUserRole(User user);
}