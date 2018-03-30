package com.lyu.csdndemo.sys.service;

import com.lyu.csdndemo.sys.dao.RoleAssociateTreeDao;
import com.lyu.csdndemo.sys.entity.RoleAssociateTree;
import com.lyu.csdndemo.sys.entity.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.lyu.csdndemo.common.base.service.GenericService;
import com.lyu.csdndemo.common.base.dao.GenericDao;

import com.lyu.csdndemo.sys.entity.UserRole;
import com.lyu.csdndemo.sys.entity.QueryUserRole;
import com.lyu.csdndemo.sys.dao.UserRoleDao;

import java.util.List;

/**
 * @author lyu
 **/
@Service("userRoleService")
@Transactional(rollbackFor = {IllegalArgumentException.class})
public class UserRoleService extends GenericService<UserRole, QueryUserRole> {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserRoleDao userRoleDao;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RoleAssociateTreeDao roleAssociateTreeDao;

    @Override
    protected GenericDao<UserRole, QueryUserRole> getDao() {
        return userRoleDao;
    }

    /**
     * 获取权限菜单数据
     *
     * @param userRole
     * @return
     */
    public UserRole getUserRoleAssociate(UserRole userRole) {
        return userRoleDao.getUserRoleAssociate(userRole);
    }

    /**
     * 批量删除角色数据
     *
     * @param entityList
     * @return
     * @throws Exception
     */
    @Override
    public boolean removeBath(List<UserRole> entityList) throws Exception {
        for (UserRole userRole : entityList) {
            roleAssociateTreeDao.removeTreeByRoleId(userRole);
        }
        return super.removeBath(entityList);
    }

    /**
     * 增加角色数据
     *
     * @param entity 保存对象
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(UserRole entity) throws Exception {
        entity.packagingTrees(entity.getTreeArray());
        List<Tree> treeList = entity.getTreeList();
        boolean success = super.save(entity);
        for (Tree tree : treeList) {
            roleAssociateTreeDao.save(new RoleAssociateTree(tree.getId(), entity.getId()));
        }
        return success;
    }

    /**
     * 更新角色数据
     *
     * @param entity 修改对象
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(UserRole entity) throws Exception {
        entity.packagingTrees(entity.getTreeArray());
        roleAssociateTreeDao.removeTreeByRoleId(entity);
        for (Tree tree : entity.getTreeList()) {
            roleAssociateTreeDao.save(new RoleAssociateTree(tree.getId(), entity.getId()));
        }
        return super.update(entity);
    }
}