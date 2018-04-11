package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import com.lyu.csdndemo.common.base.constant.SystemStaticConst;
import com.lyu.csdndemo.common.base.entity.Page;
import com.lyu.csdndemo.common.util.json.JsonHelper;
import com.lyu.csdndemo.sys.entity.QueryUser;
import com.lyu.csdndemo.sys.entity.User;
import com.lyu.csdndemo.sys.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.OrgGroup;
import com.lyu.csdndemo.sys.entity.QueryOrgGroup;
import com.lyu.csdndemo.sys.service.OrgGroupService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** @author lyu */
@Controller
@RequestMapping("/orgGroup")
public class OrgGroupController extends GenericController<OrgGroup, QueryOrgGroup> {
  @Inject private OrgGroupService orgGroupService;

  @Inject private UserService userService;

  @Override
  protected GenericService<OrgGroup, QueryOrgGroup> getService() {
    return orgGroupService;
  }

  /**
   * 跳转到更新用户页面
   *
   * @param entity
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/updateUserPage")
  public String updateUserPage(User entity, Model model) throws Exception {
    entity = userService.get(entity);
    entity.setRoleArray(
        JsonHelper.writeObject(
            Optional.ofNullable(userService.findByLogin(entity.getLogin()))
                .filter(u -> u != null)
                .orElse(new User())
                .getRoles()));
    model.addAttribute("entity", entity);
    return getPageBaseRoot() + "/updateUser";
  }

  /**
   * 跳转到添加用户页面
   *
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/addUserPage")
  public String addUserPage() throws Exception {
    return getPageBaseRoot() + "/addUserPage";
  }

  @Override
  public Map<String, Object> update(OrgGroup entity) throws Exception {
    OrgGroup update = new OrgGroup();
    update.setGroupId(entity.getGroupId());
    update = orgGroupService.get(update);
    update.setName(entity.getName());
    update.setGroupCode(entity.getGroupCode());
    update.setNum(entity.getNum());
    return super.update(update);
  }

  @Override
  public Map<String, Object> save(OrgGroup entity) throws Exception {
    String max_node =
        getMaxNode(
            orgGroupService.getMaxOrgGroup(entity.getOrgGroup().getNode()),
            entity.getOrgGroup().getNode());
    entity.setParentNode(entity.getOrgGroup().getNode());
    entity.setNode(max_node);
    return super.save(entity);
  }

  @RequestMapping(value = "/updateGroupPage")
  public String updateGroupPage(OrgGroup entity, Model model) throws Exception {
    entity = orgGroupService.get(entity);
    entity.setOrgGroup(orgGroupService.findByNode(entity.getParentNode()));
    model.addAttribute("entity", entity);
    return getPageBaseRoot() + UPDATEPAGE;
  }

  @RequestMapping(value = "/addGroupPage")
  public String addGroupPage(OrgGroup entity, Model model) throws Exception {
    entity = orgGroupService.get(entity);
    model.addAttribute("entity", entity);
    return getPageBaseRoot() + ADDPAGE;
  }

  /**
   * 获取组织架构下的相应用户
   *
   * @param user
   * @return
   */
  @RequestMapping(
    value = "/userList",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  public Map<String, Object> userList(QueryUser user) {
    Map<String, Object> result = new HashMap<>();
    Page page = userService.findByGroupUserPage(user);
    result.put("totalCount", page.getTotal());
    result.put("result", page.getRows());
    return result;
  }

  /**
   * 获取组织架构菜单树
   *
   * @return
   */
  @RequestMapping(
    value = "/loadGroupTree",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  public Map<String, Object> loadGroupTree() {
    Map<String, Object> result = new HashMap<>();
    List<OrgGroup> orgGroupList = orgGroupService.query(null);
    result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
    result.put(SystemStaticConst.MSG, "加载组织机构数据成功！");
    result.put("data", orgGroupList);
    return result;
  }

  private String getMaxNode(String node, String parentNode) {
    String max_node = "";
    if (node == null) {
      max_node = parentNode + "001";
    } else {
      String n = (Integer.parseInt(node.substring(node.length() - 3)) + 1) + "";
      switch (n.length()) {
        case 1:
          max_node = parentNode + "00" + n;
          break;
        case 2:
          max_node = parentNode + "0" + n;
          break;
        case 3:
          max_node = parentNode + "" + n;
          break;
      }
    }
    return max_node;
  }
}
