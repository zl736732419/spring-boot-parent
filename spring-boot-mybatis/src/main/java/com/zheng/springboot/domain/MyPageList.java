package com.zheng.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * 用来序列化传输的时候传输PageList
 *
 * @param <T> 对象
 * @author linshaopeng
 * @see PageList
 */
public class MyPageList<T> implements Serializable {
    
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 8294981094918202639L;
    /**
     * 分页大小
     */
    private int limit;
    /**
     * 页数
     */
    private int page;
    
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_LIMIT = 10;
    
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 结果集合
     */
    private Collection<T> items;

    /**
     * 默认无参数构造函数
     */
    public MyPageList() {
        this(new ArrayList<T>(), new Paginator(DEFAULT_PAGE, DEFAULT_LIMIT, 0));
    }

    /**
     * 以集合作为构造参数的构造函数
     *
     * @param items the items
     */
    public MyPageList(Collection<T> items) {
        setItems(items);
    }

    /**
     * 以集合和分页器作为构造参数的构造函数
     *
     * @param items     the items
     * @param paginator the paginator
     */
    public MyPageList(Collection<T> items, Paginator paginator) {
        setItems(items);
        if (paginator != null) {
            setPaginator(paginator);
        }
    }

    public MyPageList(Paginator paginator){
        this(new ArrayList<T>(),paginator);
    }

    /**
     * Instantiates a new My page list.
     *
     * @param pageList the page list
     */
    public MyPageList(PageList<T> pageList) {
        if (pageList != null) {
            Collection<T> items = new ArrayList<T>();
            if (!CollectionUtils.isEmpty(pageList)) {
                for (T t : pageList) {
                    items.add(t);
                }
                setItems(items);
            }
            if (pageList.getPaginator() != null) {
                setPaginator(pageList.getPaginator());
            }
        }
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets limit.
     *
     * @param limit the limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Collection<T> getItems() {
        if (items == null){
            items = new ArrayList<>();
        }
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     * Gets paginator.
     *
     * @return the paginator
     */
    @JsonIgnore
    public Paginator getPaginator() {
        return new Paginator(this.getPage(), this.getLimit(), this.getTotalCount());
    }

    /**
     * Sets paginator.
     *
     * @param paginator the paginator
     */
    @JsonIgnore
    protected void setPaginator(Paginator paginator) {
        this.setPage(paginator.getPage());
        this.setLimit(paginator.getLimit());
        this.setTotalCount(paginator.getTotalCount());
    }

    /**
     * @return
     @Deprecated public PageList<T> getPageList() {
     return new PageList<>(this.getItems(), new Paginator(this.getPage(), this.getLimit(), this.getTotalCount()));
     }*/

    /**
     * 用静态方法的方式转换成PageList
     *
     * @param <T>        the type parameter
     * @param myPageList the my page list
     * @return page list
     */
    public static <T> PageList<T> convertPageList(MyPageList<T> myPageList) {
        if (myPageList == null) {
            return new PageList(new PageList(new Paginator(DEFAULT_PAGE, DEFAULT_LIMIT, 0)));
        }

        Collection<T> items = myPageList.getItems();
        if (!Optional.ofNullable(items).isPresent()) {
            items = new ArrayList<>();
        }

        PageList<T> pageList = new PageList<>(items, myPageList.getPaginator());
        return pageList;
    }
}