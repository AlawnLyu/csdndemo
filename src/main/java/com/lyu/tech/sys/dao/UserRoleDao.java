package com.lyu.tech.sys.dao;

import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.UserRole;
import com.lyu.tech.sys.entity.QueryUserRole;

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