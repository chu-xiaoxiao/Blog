package com.zyc.model;


public class User {
	private int id;
	private String username;
	private String userpassword;
	private Integer usertype;
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param username
	 * @param userpassword
	 * @param usertype
	 */
	public User(int id, String username, String userpassword, Integer usertype) {
		super();
		this.id = id;
		this.username = username;
		this.userpassword = userpassword;
		this.usertype = usertype;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPssword() {
		return userpassword;
	}
	public void setPssword(String pssword) {
		this.userpassword = pssword;
	}
	
	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", userpassword=" + userpassword + ", usertype=" + usertype
				+ "]";
	}
	
}
