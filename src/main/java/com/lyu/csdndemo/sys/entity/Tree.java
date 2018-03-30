package com.lyu.csdndemo.sys.entity;

/**
 * @author lyu
 **/
public class Tree implements Comparable<Tree> {
    private long id;
    private String code;
    private String icon;
    private String name;
    private long pId;
    private long treeOrder;
    private String url;
    private String state;

    public Tree() {
        super();
    }

    public Tree(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPId() {
        return pId;
    }

    public void setPId(long pId) {
        this.pId = pId;
    }

    public long getTreeOrder() {
        return treeOrder;
    }

    public void setTreeOrder(long treeOrder) {
        this.treeOrder = treeOrder;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 实现集合根据treeOrder字段进行排序的功能
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Tree o) {
        long i = this.getTreeOrder() - o.getTreeOrder();
        return Integer.parseInt(i + "");
    }
}