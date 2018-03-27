package com.lyu.csdndemo.common.base.entity;

import java.util.List;

/**
 * 分页实体类
 */
@SuppressWarnings("rawtypes")
public class Page {
    private List rows;
    private Long total;

    public Page() {
    }

    public Page(List rows, Long total) {
        super();
        this.rows = rows;
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page [rows=" + rows + ", total=" + total + "]";
    }
}
