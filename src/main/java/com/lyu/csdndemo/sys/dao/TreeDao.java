package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.entity.QueryTree;
import com.lyu.csdndemo.sys.entity.User;

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