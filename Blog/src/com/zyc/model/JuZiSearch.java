package com.zyc.model;

public class JuZiSearch {
	private String juzileixing;
	private String juzichuchu;
	/**
	 * 
	 */
	public JuZiSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param juzileixing
	 * @param juzichuchu
	 */
	public JuZiSearch(String juzileixing, String juzichuchu) {
		super();
		this.juzileixing = juzileixing;
		this.juzichuchu = juzichuchu;
	}
	public String getJuzileixing() {
		return juzileixing;
	}
	public void setJuzileixing(String juzileixing) {
		this.juzileixing = juzileixing;
	}
	public String getJuzichuchu() {
		return juzichuchu;
	}
	public void setJuzichuchu(String juzichuchu) {
		this.juzichuchu = juzichuchu;
	}
	
}
