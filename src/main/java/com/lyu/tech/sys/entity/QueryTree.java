package com.lyu.tech.sys.entity;

import com.lyu.tech.common.base.entity.QueryBase;

/** @author lyu */
public class QueryTree extends QueryBase {
  private String code;
  private String icon;
  private String name;
  private Long pId;
  private Long treeOrder;
  private String url;
  private String state;
  private String display;

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

  public Long getpId() {
    return pId;
  }

  public void setpId(Long pId) {
    this.pId = pId;
  }

  public Long getTreeOrder() {
    return treeOrder;
  }

  public void setTreeOrder(Long treeOrder) {
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

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }
}
