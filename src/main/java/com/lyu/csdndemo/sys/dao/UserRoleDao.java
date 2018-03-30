package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.UserRole;
import com.lyu.csdndemo.sys.entity.QueryUserRole;

/**
 * @author lyu
 **/
public interface UserRoleDao extends GenericDao<UserRole, QueryUserRole> {

    /**
     * 获取权限菜单数据
     *
     * @param entity
     * @return
     */
    UserRole getUserRoleAssociate(UserRole entity);
}