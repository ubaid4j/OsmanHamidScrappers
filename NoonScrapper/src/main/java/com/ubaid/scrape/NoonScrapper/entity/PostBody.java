package com.ubaid.scrape.NoonScrapper.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostBody implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4974895379736403479L;
	private List<String> brand = new ArrayList<String>();
	private List<String> category = new ArrayList<String>();
	private List<String> filterKey = new ArrayList<String>();
	private Object f = new Object();
	private Object sort = new Object();
	private Integer limit;
	private Integer page;
	public PostBody(List<String> brand, List<String> category, List<String> filterKey, Object f, Sort sort,
			Integer limit, Integer page) {
		super();
		this.brand = brand;
		this.category = category;
		this.filterKey = filterKey;
		this.f = f;
		this.sort = sort;
		this.limit = limit;
		this.page = page;
	}
	public List<String> getBrand() {
		return brand;
	}
	public void setBrand(List<String> brand) {
		this.brand = brand;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public List<String> getFilterKey() {
		return filterKey;
	}
	public void setFilterKey(List<String> filterKey) {
		this.filterKey = filterKey;
	}
	public Object getF() {
		return f;
	}
	public void setF(Object f) {
		this.f = f;
	}
	public Object getSort() {
		return sort;
	}
	public void setSort(Object sort) {
		this.sort = sort;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public PostBody() {
		super();
	}
	@Override
	public String toString() {
		return "PostBody [brand=" + brand + ", category=" + category + ", filterKey=" + filterKey + ", f=" + f
				+ ", sort=" + sort + ", limit=" + limit + ", page=" + page + "]";
	}
	
	
	
	

}

class Sort
{
	private String by;
	private String dir;
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Sort(String by, String dir) {
		super();
		this.by = by;
		this.dir = dir;
	}
	@Override
	public String toString() {
		return "Sort [by=" + by + ", dir=" + dir + "]";
	}
	public Sort() {
		super();
	}
	
	
}