package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.RoleAssociateTree;
import com.lyu.csdndemo.sys.entity.QueryRoleAssociateTree;
import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.entity.UserRole;

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