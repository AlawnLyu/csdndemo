package com.lyu.tech.sys.dao;

import com.lyu.tech.common.base.dao.GenericDao;

import com.lyu.tech.sys.entity.Tree;
import com.lyu.tech.sys.entity.QueryTree;
import com.lyu.tech.sys.entity.User;

import java.util.List;

/**
 * @author lyu
 **/
public interface TreeDao extends GenericDao<Tree, QueryTree> {

    /**
     * 加载用户菜单树的数据
     *
     * @param user
     * @return
     */
    List<Tree> loadUserTree(User user);
}