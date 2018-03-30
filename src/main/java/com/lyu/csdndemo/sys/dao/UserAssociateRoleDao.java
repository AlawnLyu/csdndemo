package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.User;
import com.lyu.csdndemo.sys.entity.UserAssociateRole;
import com.lyu.csdndemo.sys.entity.QueryUserAssociateRole;

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