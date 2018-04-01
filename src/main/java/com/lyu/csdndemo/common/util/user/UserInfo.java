package com.lyu.csdndemo.common.util.user;

import com.lyu.csdndemo.common.util.node.NodeUtil;
import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.entity.User;
import com.lyu.csdndemo.sys.service.TreeService;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserInfo {

    /**
     * 实现对密码加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, "lyu");
    }

    /**
     * 加载节点数据
     *
     * @param treeService
     * @return
     */
    public static List<Tree> loadUserTree(TreeService treeService) {
        Map<Long, Tree> treeMap = new HashMap<>();
        User user = getUser();
        for (Tree tree : treeService.LoadUserTree(user)) {
            treeMap.put(tree.getId(), tree);
        }
        List<Tree> treeList = NodeUtil.getChildNode(new ArrayList<>(treeMap.values()), 0l);
        return treeList;
    }

    /**
     * 获取当前登录用户的信息
     *
     * @return
     */
    public static User getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        SecurityContextImpl securityContext = (SecurityContextImpl) request.getSession().getAttribute
                ("SPRING_SECURITY_CONTEXT");
        return (User) Optional.ofNullable(securityContext.getAuthentication().getPrincipal()).orElse(null);
    }
}
