package com.zyc.model;

import java.util.Date;

public class Juzi {
    private Integer juziid;

    private String juzineirong;

    private String juzichuchu;

    private Integer juzileixing;

    private Date tianjiashijian;
    
    private JuziTypeKey juziTypeKey;

    public Integer getJuziid() {
        return juziid;
    }

    public void setJuziid(Integer juziid) {
        this.juziid = juziid;
    }

    public String getJuzineirong() {
        return juzineirong;
    }

    public void setJuzineirong(String juzineirong) {
        this.juzineirong = juzineirong;
    }

    public String getJuzichuchu() {
        return juzichuchu;
    }

    public void setJuzichuchu(String juzichuchu) {
        this.juzichuchu = juzichuchu;
    }

    public Integer getJuzileixing() {
        return juzileixing;
    }

    public void setJuzileixing(Integer juzileixing) {
        this.juzileixing = juzileixing;
    }

    public Date getTianjiashijian() {
        return tianjiashijian;
    }

    public void setTianjiashijian(Date tianjiashijian) {
        this.tianjiashijian = tianjiashijian;
    }

	public JuziTypeKey getJuziTypeKey() {
		return juziTypeKey;
	}

	public void setJuziTypeKey(JuziTypeKey juziTypeKey) {
		this.juziTypeKey = juziTypeKey;
	}

    @Override
    public String toString() {
        return "Juzi{" +
                "juziid=" + juziid +
                ", juzineirong='" + juzineirong + '\'' +
                ", juzichuchu='" + juzichuchu + '\'' +
                ", juzileixing=" + juzileixing +
                ", tianjiashijian=" + tianjiashijian +
                ", juziTypeKey=" + juziTypeKey +
                '}';
    }
}