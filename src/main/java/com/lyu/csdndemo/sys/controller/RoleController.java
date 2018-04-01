package com.lyu.csdndemo.sys.controller;

import javax.inject.Inject;

import com.lyu.csdndemo.common.base.constant.SystemStaticConst;
import com.lyu.csdndemo.sys.entity.Tree;
import com.lyu.csdndemo.sys.mapper.TreeMapper;
import com.lyu.csdndemo.sys.service.TreeService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.lyu.csdndemo.common.base.controller.GenericController;
import com.lyu.csdndemo.common.base.service.GenericService;

import com.lyu.csdndemo.sys.entity.UserRole;
import com.lyu.csdndemo.sys.entity.QueryUserRole;
import com.lyu.csdndemo.sys.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyu
 **/
@Controller
@RequestMapping("/role")
public class RoleController extends GenericController<UserRole, QueryUserRole> {

    @Inject
    private UserRoleService userRoleService;

    @Inject
    private TreeService treeService;

    @Inject
    private TreeMapper treeMapper;

    @Override
    protected GenericService<UserRole, QueryUserRole> getService() {
        return userRoleService;
    }

    /**
     * 根据用户的权限去加载角色数据
     *
     * @param entity
     * @return
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/loadRoleTree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> loadRoleTree(UserRole entity) {
        Map<String, Object> result = new HashMap<>();
        entity = userRoleService.getUserRoleAssociate(entity);
        List<Tree> treeList = treeService.query(null);
        if (entity != null) {
            for (Tree tree : entity.getTreeList()) {
                treeList.stream().forEach(t -> {
                    if (t.getId() == tree.getId()) {
                        t.setChecked(true);
                    }
                });
            }
        }
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        result.put("data", treeMapper.treeToTreeDTOs(treeList));
        return result;
    }
}