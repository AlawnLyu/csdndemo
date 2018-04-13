package com.lyu.tech.sys.controller;

import javax.inject.Inject;

import com.lyu.tech.common.base.constant.SystemStaticConst;
import com.lyu.tech.sys.mapper.TreeMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.tech.common.base.controller.GenericController;
import com.lyu.tech.common.base.service.GenericService;

import com.lyu.tech.sys.entity.Tree;
import com.lyu.tech.sys.entity.QueryTree;
import com.lyu.tech.sys.service.TreeService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @author lyu */
@Controller
@RequestMapping("/tree")
public class TreeController extends GenericController<Tree, QueryTree> {
  @Inject private TreeService treeService;

  @Inject private TreeMapper treeMapper;

  @Override
  protected GenericService<Tree, QueryTree> getService() {
    return treeService;
  }

  /**
   * 跳转到修改菜单节点页面
   *
   * @param entity
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/updateTreePage")
  public String updateTreePage(Tree entity, Model model) throws Exception {
    entity = treeService.get(entity);
    Tree pTree = null;
    if (entity.getpId() == 0) {
      pTree = new Tree();
      pTree.setId(0);
      pTree.setName("顶层节点");
    } else {
      pTree = treeService.get(new Tree(entity.getpId()));
    }
    entity.setTree(pTree);
    model.addAttribute("entity", entity);
    return getPageBaseRoot() + UPDATEPAGE;
  }

  /**
   * 跳转到增加菜单节点的页面
   *
   * @param entity
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/addTreePage")
  public String addTreePage(Tree entity, Model model) throws Exception {
    if (entity.getId() == 0) {
      entity = new Tree();
      entity.setId(0);
      entity.setName("顶层节点");
    } else {
      entity = treeService.get(entity);
    }
    model.addAttribute("entity", entity);
    return getPageBaseRoot() + ADDPAGE;
  }

  /**
   * 直接加载整个菜单树的数据(且必须要有管理员权限才可以加载该菜单树的数据)
   *
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(
    value = "/loadUserTree",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseBody
  public Map<String, Object> loadUserTree() {
    Map<String, Object> result = new HashMap<>();
    List<Tree> treeList = treeService.query(null);
    result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
    result.put(SystemStaticConst.MSG, "加载菜单数据成功！");
    result.put("data", treeMapper.treeToTreeDTOs(treeList));
    return result;
  }
}
