package com.lyu.csdndemo.sys.service;

import com.lyu.csdndemo.common.base.dao.GenericDao;
import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.sys.dao.UserDao;
import com.lyu.csdndemo.sys.entity.QueryUser;
import com.lyu.csdndemo.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional(rollbackFor = {IllegalArgumentException.class})
public class UserService extends GenericService<User, QueryUser> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserDao userDao;

    @Override
    protected GenericDao<User, QueryUser> getDao() {
        return userDao;
    }
}
