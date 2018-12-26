package com.mini.model;

/**
 * Created by xiaodou on 2018/12/2618:08.
 */

public class DingOragtion {
    private String id;
    private String createDeptGroup;
    private String name;
    private String parentid;
    private String autoAddUser;
    private String sourceIdentifier;

    public String getId() {
        return id;
    }

    public String getCreateDeptGroup() {
        return createDeptGroup;
    }

    public String getName() {
        return name;
    }

    public String getParentid() {
        return parentid;
    }

    public String getAutoAddUser() {
        return autoAddUser;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreateDeptGroup(String createDeptGroup) {
        this.createDeptGroup = createDeptGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public void setAutoAddUser(String autoAddUser) {
        this.autoAddUser = autoAddUser;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }
}
