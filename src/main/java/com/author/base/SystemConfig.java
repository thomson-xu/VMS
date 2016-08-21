package com.author.base;

/**
 * Created by Administrator on 2016/8/8.
 */

import com.author.annotations.ConfigElement;
import com.author.annotations.Document;
import com.author.base.common.config.DateFormatter;
import com.author.base.common.config.ListReader;
import com.author.base.common.config.MapReader;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(
        path = "config/systemConfig.xml",
        root = true
)
public class SystemConfig {
    @ConfigElement(
            tag = "copyright"
    )
    private String copyright;
    @ConfigElement(
            tag = "developers",
            type = "map",
            reader = MapReader.class
    )
    private Map developers;
    @ConfigElement(
            tag = "post-sales",
            type = "list",
            reader = ListReader.class
    )
    private List postSales;
    @ConfigElement(
            tag = "technical-support",
            type = "list",
            reader = ListReader.class
    )
    private List technicalSupport;
    @ConfigElement(
            tag = "qq-group",
            type = "map",
            reader = MapReader.class
    )
    private Map qqGroup;
    @ConfigElement(
            tag = "service-hotline",
            type = "list",
            reader = ListReader.class
    )
    private List serviceHotline;
    @ConfigElement(
            tag = "email"
    )
    private String email;
    @ConfigElement(
            tag = "customer"
    )
    private String customer;
    @ConfigElement(
            tag = "key"
    )
    private String key;
    @ConfigElement(
            tag = "valid-until",
            type = "date",
            fomatter = DateFormatter.class
    )
    private Date validUntil;
    @ConfigElement(
            tag = "webapp",
            type = "map",
            reader = MapReader.class
    )
    private Map webapp;

    public SystemConfig() {
    }

    public String getCopyright() {
        return this.copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Map getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(Map developers) {
        this.developers = developers;
    }

    public List getPostSales() {
        return this.postSales;
    }

    public void setPostSales(List postSales) {
        this.postSales = postSales;
    }

    public List getTechnicalSupport() {
        return this.technicalSupport;
    }

    public void setTechnicalSupport(List technicalSupport) {
        this.technicalSupport = technicalSupport;
    }

    public Map getQqGroup() {
        return this.qqGroup;
    }

    public void setQqGroup(Map qqGroup) {
        this.qqGroup = qqGroup;
    }

    public List getServiceHotline() {
        return this.serviceHotline;
    }

    public void setServiceHotline(List serviceHotline) {
        this.serviceHotline = serviceHotline;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getValidUntil() {
        return this.validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Map getWebapp() {
        return this.webapp;
    }

    public void setWebapp(Map webapp) {
        this.webapp = webapp;
    }
}
