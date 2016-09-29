package com.tms.base.model;

/**
 * Created by Administrator on 2016/8/8.
 */
import java.util.Date;

public class Parameters {
    private int start;
    private int limit;
    private int page;
    private int springDataPage;
    private String id;
    private Date startDate;
    private Date endDate;
    private String name;
    private String username;
    private String depId;
    private String orders;
    private String[] orderarr;
    private String className;
    private String content;
    private String field;
    private String value;
    private int dyz;
    private String params;
    private Integer level;
    private String VBzh;
    private Integer IXzlx;
    private Integer IJb;

    public Parameters() {
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
        this.springDataPage = page - 1;
    }

    public int getSpringDataPage() {
        return this.springDataPage;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrders() {
        return this.orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
        this.orderarr = this.orders.split(",");
    }

    public String[] getOrderarr() {
        return this.orderarr;
    }

    public void setOrderarr(String[] orderarr) {
        this.orderarr = orderarr;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepId() {
        return this.depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDyz() {
        return this.dyz;
    }

    public void setDyz(int dyz) {
        this.dyz = dyz;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getVBzh() {
        return this.VBzh;
    }

    public void setVBzh(String bzh) {
        this.VBzh = bzh;
    }

    public Integer getIXzlx() {
        return this.IXzlx;
    }

    public void setIXzlx(Integer xzlx) {
        this.IXzlx = xzlx;
    }

    public Integer getIJb() {
        return this.IJb;
    }

    public void setIJb(Integer jb) {
        this.IJb = jb;
    }
}
