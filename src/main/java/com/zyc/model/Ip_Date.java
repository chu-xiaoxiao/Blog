package com.zyc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * IP与时间的映射关系类
 * @author YuChen Zhang
 *
 */
public class Ip_Date {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	private Integer count;
	public Ip_Date() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		date = simpleDateFormat.parse(simpleDateFormat.format(date));
		this.date = date;
	}


	public Ip_Date(Date date, Integer count) {
		super();
		this.date = date;
		this.count = count;
	}


	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
