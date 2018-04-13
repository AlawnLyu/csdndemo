package com.lyu.tech.common.config.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import javax.inject.Inject;

/** STOMP监听类 用于session注册 以及key值获取 */
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectEvent> {

  @Inject SocketSessionRegistry socketSessionRegistry;

  @Override
  public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
    StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
    // login get from browser
    String agentId = sha.getNativeHeader("login").get(0);
    String sessionId = sha.getSessionId();
    socketSessionRegistry.registerSessionId(agentId, sessionId);
  }
}
