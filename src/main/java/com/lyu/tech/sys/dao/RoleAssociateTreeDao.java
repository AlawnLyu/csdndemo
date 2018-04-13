package com.lyu.tech.sys.dao;

import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.RoleAssociateTree;
import com.lyu.tech.sys.entity.QueryRoleAssociateTree;
import com.lyu.tech.sys.entity.Tree;
import com.lyu.tech.sys.entity.UserRole;

/**
 * @author lyu
 **/
public interface RoleAssociateTreeDao extends GenericDao<RoleAssociateTree, QueryRoleAssociateTree> {

    /**
     * 根据菜单ID来删除关联的菜单数据
     *
     * @param tree
     * @return
     */
    int removeTreeByTreeId(Tree tree);

    /**
     * 根据角色ID来删除关联的菜单数据
     *
     * @param userRole
     * @return
     */
    int removeTreeByRoleId(UserRole userRole);
}