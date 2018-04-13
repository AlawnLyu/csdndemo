package com.lyu.tech.sys.entity;

/**
 * @author lyu
 **/
public class RoleAssociateTree {
    private long roleId;
    private long treeId;

    public RoleAssociateTree() {
        super();
    }

    public RoleAssociateTree(long treeId, long roleId) {
        this.roleId = roleId;
        this.treeId = treeId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getTreeId() {
        return treeId;
    }

    public void setTreeId(long treeId) {
        this.treeId = treeId;
    }

}