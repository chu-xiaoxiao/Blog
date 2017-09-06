package com.zyc.model;

import java.util.Date;

public class IP {
    private Integer ipid;

    private String ip;

    private String location;

    private Date date;

    public Integer getIpid() {
        return ipid;
    }

    public void setIpid(Integer ipid) {
        this.ipid = ipid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}