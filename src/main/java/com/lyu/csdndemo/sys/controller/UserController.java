package com.lyu.csdndemo.sys.controller;

import com.lyu.csdndemo.common.base.constant.SystemStaticConst;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.common.util.json.JsonHelper;
import com.lyu.csdndemo.common.util.user.UserInfo;
import com.lyu.csdndemo.sys.entity.QueryUser;
import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.entity.User;
import com.lyu.csdndemo.sys.entity.UserRole;
import com.lyu.csdndemo.sys.service.TreeService;
import com.lyu.csdndemo.sys.service.UserRoleService;
import com.lyu.csdndemo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User, QueryUser> {

    @Inject
    private UserService userService;

    @Inject
    private TreeService treeService;

    @Inject
    private UserRoleService userRoleService;

    @Override
    protected GenericService<User, QueryUser> getService() {
        return userService;
    }

    /**
     * 加载首页菜单节点的数据
     *
     * @return
     */
    @RequestMapping(value = "/mainTree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> mainTree() {
        Map<String, Object> result = new HashMap<>();
        List<Tree> trees = UserInfo.loadUserTree(treeService);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", trees);
        return result;
    }

    /**
     * 更新用户状态为禁用/启用
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userControl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> userControl(User entity) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (userService.userControl(entity)) {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
            result.put(SystemStaticConst.MSG, "更新用户状态成功！");
            result.put("entity", entity);
        } else {
            result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
            result.put(SystemStaticConst.MSG, "更新用户状态失败！");
        }
        return result;
    }

    /**
     * 跳转到更新用户的页面
     *
     * @param entity
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String updatePage(User entity, Model model) throws Exception {
        entity = getService().get(entity);
        entity.setRoleArray(JsonHelper.list2json(Optional.ofNullable(userService.findByLogin(entity.getLogin()))
                .filter(u -> u != null).orElse(new User()).getRoles()));
        model.addAttribute("entity", entity);
        return getPageBaseRoot() + UPDATEPAGE;
    }

    /**
     * 加载所有的权限数据
     *
     * @return
     */
    @RequestMapping(value = "/loadRoles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> loadRoles() {
        Map<String, Object> result = new HashMap<>();
        List<UserRole> list = userRoleService.query(null);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("list", list);
        return result;
    }

    /**
     * 跳转到选择组织架构页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/group")
    public String pickGroup() throws Exception {
        return getPageBaseRoot() + "/group";
    }
}
