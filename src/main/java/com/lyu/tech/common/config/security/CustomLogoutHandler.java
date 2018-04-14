package com.lyu.tech.common.config.security;

import com.lyu.tech.common.util.redis.RedisCache;
import com.lyu.tech.sys.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

  private static final Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);

  @Autowired private RedisCache redisCache;

  @Override
  public void logout(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Authentication authentication) {
    if (authentication != null) {
      User user = (User) authentication.getPrincipal();
      boolean result = redisCache.del("userinfo-" + user.getLogin());
      if (logger.isDebugEnabled()) {
        logger.debug("{}退出登录，清除用户缓存{}", user.getLogin(), result ? "成功" : "失败");
      }
    }
  }
}
