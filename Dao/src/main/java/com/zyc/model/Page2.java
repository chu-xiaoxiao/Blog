package com.zyc.model;

import java.util.List;


/**
 * @author YuChen Zhang
 *
 */
public class Page2<T,E> {
	/**
	 * 查询条件
	 */
	private E e;
	private Integer currentPage;
	private Integer size;
	private Integer allPage;
	private List<T> lists;
	private Integer start;
	private Integer end;
	
	public Page2(E e, Integer currentPage, Integer size) {
		super();
		this.e = e;
		if(currentPage!=null){
			this.currentPage = currentPage;
		}else{
			this.currentPage=0;
		}
		if(size!=null){
			this.size = size;
		}else{
			this.size=10;
		}
		this.allPage = allPage;
		this.lists = lists;
		this.start = this.size*this.currentPage;
		this.end =(this.size+1)*this.currentPage;
	}
	public Page2(E e, String currentPage, String size) {
		super();
		this.e = e;
		if(currentPage!=null){
			this.currentPage = Integer.parseInt(currentPage);
		}else{
			this.currentPage=0;
		}
		if(size!=null){
			this.size = Integer.parseInt(size);
		}else{
			this.size=10;
		}
		this.allPage = allPage;
		this.lists = lists;
		this.start = this.size*this.currentPage;
		this.end =(this.size+1)*this.currentPage;
	}
	public Integer countAllPage(Integer allRows){
		return (allRows+this.size-1)/this.size;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public E getE() {
		return e;
	}
	public void setE(E e) {
		this.e = e;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public Integer getStatr() {
		return start;
	}
	public void setStatr(Integer statr) {
		this.start = statr;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
}
