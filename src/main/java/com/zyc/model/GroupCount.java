package com.zyc.model;

/**
 * Created by YuChen Zhang on 17/11/08.
 */
public class GroupCount {
    Object group;
    Integer count;

    public GroupCount() {
    }

    public GroupCount(Object group, Integer count) {
        this.group = group;
        this.count = count;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
