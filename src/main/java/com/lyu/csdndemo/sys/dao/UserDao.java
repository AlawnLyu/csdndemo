package com.lyu.csdndemo.sys.dao;

import com.lyu.csdndemo.common.base.dao.GenericDao;
import com.lyu.csdndemo.sys.entity.QueryUser;
import com.lyu.csdndemo.sys.entity.User;

public interface UserDao extends GenericDao<User, QueryUser> {
    /**
     * 功能描述：根据账号来获取用户信息
     *
     * @param login
     * @return
     */
    User findByLogin(String login);
}
