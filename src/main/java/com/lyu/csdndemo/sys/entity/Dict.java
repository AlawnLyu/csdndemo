package com.lyu.csdndemo.sys.entity;

/** @author lyu */
public class Dict {
  // 主键
  private int id;
  // 代码
  private String code;
  // 描述
  private String text;
  // 类型
  private String type;
  // 值
  private String value;

  private String isLoad;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getIsLoad() {
    return isLoad;
  }

  public void setIsLoad(String isLoad) {
    this.isLoad = isLoad;
  }
}
