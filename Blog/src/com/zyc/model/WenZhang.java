package com.zyc.model;

import java.util.Date;

public class WenZhang {
	private Integer wenzhangid;
	private String wenzhangneirong;
	private String wenzhangbiaoti;
	private String wenzhangleixing;
	private String wenzhangchunwenben;
	private Date wenzhangriqi;
	/**
	 * 
	 */
	public WenZhang() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param wenzhangneirong
	 * @param wenzhangbiaoti
	 * @param wenzhangleixing
	 * @param wenzhangriqi
	 */
	public WenZhang(Integer id, String wenzhangneirong, String wenzhangbiaoti, String wenzhangleixing,
			Date wenzhangriqi) {
		super();
		this.wenzhangid = id;
		this.wenzhangneirong = wenzhangneirong;
		this.wenzhangbiaoti = wenzhangbiaoti;
		this.wenzhangleixing = wenzhangleixing;
		this.wenzhangriqi = wenzhangriqi;
	}
	public Integer getId() {
		return wenzhangid;
	}
	public void setId(Integer id) {
		this.wenzhangid = id;
	}
	public String getWenzhangneirong() {
		return wenzhangneirong;
	}
	public void setWenzhangneirong(String wenzhangneirong) {
		this.wenzhangneirong = wenzhangneirong;
	}
	public String getWenzhangleixing() {
		return wenzhangleixing;
	}
	public void setWenzhangleixing(String wenzhangleixing) {
		this.wenzhangleixing = wenzhangleixing;
	}
	public Date getWenzhangriqi() {
		return wenzhangriqi;
	}
	public void setWenzhangriqi(Date wenzhangriqi) {
		this.wenzhangriqi = wenzhangriqi;
	}

	public String getWenzhangbiaoti() {
		return wenzhangbiaoti;
	}

	public void setWenzhangbiaoti(String wenzhangbiaoti) {
		this.wenzhangbiaoti = wenzhangbiaoti;
	}
	

	public Integer getWenzhangid() {
		return wenzhangid;
	}

	public void setWenzhangid(Integer wenzhangid) {
		this.wenzhangid = wenzhangid;
	}

	public String getWenzhangchunwenben() {
		return wenzhangchunwenben;
	}

	public void setWenzhangchunwenben(String wenzhangchunwenben) {
		this.wenzhangchunwenben = wenzhangchunwenben;
	}

	@Override
	public String toString() {
		return "WenZhang [id=" + wenzhangid + ", wenzhangneirong=" + wenzhangneirong + ", wenzhangbiaoti=" + wenzhangbiaoti
				+ ", wenzhangleixing=" + wenzhangleixing + ", wenzhangriqi=" + wenzhangriqi + "]";
	}
	
	
}
