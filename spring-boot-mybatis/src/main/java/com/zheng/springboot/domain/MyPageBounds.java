package com.zheng.springboot.domain;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来dubbo序列化传输的时候传输PageBounds参数
 *
 * @see PageBounds
 */
public class MyPageBounds implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4186603958693112362L;

    private Integer page;
    private Integer limit;
    private List<Order> orders = new ArrayList<>();

    /**
     * 结果集是否包含TotalCount
     */
    private boolean containsTotalCount = true;
    protected Boolean asyncTotalCount = true;

    public MyPageBounds() {

    }

    public MyPageBounds(int page, int limit) {
        setPage(page);
        setLimit(limit);
    }

    public MyPageBounds(PageBounds pageBounds) {
        this(pageBounds.getPage(), pageBounds.getLimit());
        this.setOrders(pageBounds.getOrders());
        this.setAsyncTotalCount(pageBounds.getAsyncTotalCount());
        this.setContainsTotalCount(pageBounds.isContainsTotalCount());
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public PageBounds getPageBounds() {
        PageBounds pageBounds = new PageBounds(page, limit);
        pageBounds.setOrders(this.getOrders());
        pageBounds.setContainsTotalCount(this.isContainsTotalCount());
        pageBounds.setAsyncTotalCount(this.getAsyncTotalCount());
        return pageBounds;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isContainsTotalCount() {
        return containsTotalCount;
    }

    public void setContainsTotalCount(boolean containsTotalCount) {
        this.containsTotalCount = containsTotalCount;
    }

    public Boolean getAsyncTotalCount() {
        return asyncTotalCount;
    }

    public void setAsyncTotalCount(Boolean asyncTotalCount) {
        this.asyncTotalCount = asyncTotalCount;
    }
}