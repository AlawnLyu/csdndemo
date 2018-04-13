package com.lyu.tech.common.config.security;

import com.lyu.tech.common.util.redis.RedisCache;
import com.lyu.tech.sys.dao.UserDao;
import com.lyu.tech.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import java.util.Date;

public class CustomUserService implements UserDetailsService {

  @Inject private UserDao userDao;
  @Autowired private RedisCache redisCache;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userDao.findByLogin(s);
    if (user == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }
    if (user.getState().equalsIgnoreCase("0")) {
      throw new LockedException("用户账号被冻结，无法登陆请联系管理员！");
    }
    redisCache.setObject("userinfo-" + s, user);
    redisCache.expire("userinfo-" + s, 3600);
    user.setLastLoginDate(new Date());
    userDao.updateLogin(user);
    return user;
  }
}
