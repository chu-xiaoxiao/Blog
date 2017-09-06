package com.zyc.model;

public class WenZhangSearch {
	private String type;
	private String name;
	/**
	 * 
	 */
	public WenZhangSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param type
	 * @param name
	 */
	public WenZhangSearch(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
