package com.zyc.model;

public class JuZi {
	private Integer juziid;
	private String juzineirong;
	private String juzichuchu;
	private String tianjiashijian;
	/**
	 * 
	 */
	public JuZi() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param juziid
	 * @param juzineirong
	 * @param juzichuchu
	 * @param tianjiashijian
	 */
	public JuZi(Integer juziid, String juzineirong, String juzichuchu, String tianjiashijian) {
		super();
		this.juziid = juziid;
		this.juzineirong = juzineirong;
		this.juzichuchu = juzichuchu;
		this.tianjiashijian = tianjiashijian;
	}
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
	public String getTianjiashijian() {
		return tianjiashijian;
	}
	public void setTianjiashijian(String tianjiashijian) {
		this.tianjiashijian = tianjiashijian;
	}
	@Override
	public String toString() {
		return "JuZi [juziid=" + juziid + ", juzineirong=" + juzineirong + ", juzichuchu=" + juzichuchu
				+ ", tianjiashijian=" + tianjiashijian + "]";
	}
	
}
