package com.lyu.tech.sys.controller;

import com.lyu.tech.common.config.websocket.OutMessage;
import com.lyu.tech.common.config.websocket.SocketSessionRegistry;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/websocket")
public class WebsocketController {

    /**
     * session操作类
     */
    @Inject
    private SocketSessionRegistry socketSessionRegistry;

    /**
     * 消息发送工具
     */
    @Inject
    private SimpMessagingTemplate template;

    /**
     * 给全局推送消息
     */
    @RequestMapping(value = "/sendAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendAll() {
        Map<String, Set<String>> all = socketSessionRegistry.getAllSessionIds();
        JSONObject jobj = new JSONObject();
        jobj.put("test", "test");
        all.forEach((k, v) -> v.forEach(x -> template.convertAndSendToUser(x, "/topic/greetings", new OutMessage(jobj.toString()), createHeaders(x))));
    }

    /**
     * 给指定用户推送消息
     */
    @RequestMapping(value = "/sendUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendUser() {
        // 此处账号已经被写死为lyu 大家可以根据自己的实际业务来修改此处的代码
        Set<String> keys = socketSessionRegistry.getSessionIds("lyu");
        JSONObject jobj = new JSONObject();
        jobj.put("lyu", "lyu");
        keys.forEach(x -> {
            template.convertAndSendToUser(x, "/topic/greetings", new OutMessage(jobj.toString()), createHeaders(x));
        });
    }

    /**
     * 组装JSON数据的头部数据
     *
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
