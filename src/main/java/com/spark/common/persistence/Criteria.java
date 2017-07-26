/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.spark.common.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spark.common.config.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 分页类
 * @author ThinkGem
 * @version 2013-7-2
 */
public class Criteria<V> extends Constants {
	
	private int pageNum = 1; // 当前页码
	private int pageSize = -1; // 页面大小，设置为“-1”表示不进行分页（分页无效）
	
	private long totalCount;// 总记录数，设置为“-1”表示不查询总数
	
	@JsonIgnore
	private int first;// 首页索引
	@JsonIgnore
	private int last;// 尾页索引
	@JsonIgnore
	private int prev;// 上一页索引
	@JsonIgnore
	private int next;// 下一页索引
	@JsonIgnore
	private boolean firstPage;//是否是第一页
	@JsonIgnore
	private boolean lastPage;//是否是最后一页
	@JsonIgnore
	private Map<String, Object> param = new HashMap<>();
	private List<V> result = new ArrayList<V>();
	@JsonIgnore
	private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc
	
	private long howMuchCount = 0;// 当前页后面还有多少条数据没查询过

	public Criteria() {
		this.pageSize = -1;
		this.totalCount = -1;
	}

	/**
	 * 构造方法
	 * @param pageNum 当前页码
	 * @param pageSize 分页大小
	 */
	public Criteria(int pageNum, int pageSize) {
		this(pageNum, pageSize, 0);
	}
	
	/**
	 * 构造方法
	 * @param pageNum 当前页码
	 * @param pageSize 分页大小
	 * @param totalCount 数据条数
	 */
	public Criteria(int pageNum, int pageSize, long totalCount) {
		this(pageNum, pageSize, totalCount, new ArrayList<V>());
	}
	
	/**
	 * 构造方法
	 * @param pageNum 当前页码
	 * @param pageSize 分页大小
	 * @param totalCount 数据条数
	 * @param list 本页数据对象列表
	 */
	public Criteria(int pageNum, int pageSize, long totalCount, List<V> list) {
		this.setTotalCount(totalCount);
		this.setPageNum(pageNum);
		this.pageSize = pageSize;
		this.result = list;
	}
	
	/**
	 * 初始化参数
	 */
	public void initialize(){
				
		//1
		this.first = 1;
		
		this.last = (int)(totalCount / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);
		
		if (this.totalCount % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}
		
		if (this.pageNum <= 1) {
			this.pageNum = this.first;
			this.firstPage=true;
		}

		if (this.pageNum >= this.last) {
			this.pageNum = this.last;
			this.lastPage=true;
		}

		if (this.pageNum < this.last - 1) {
			this.next = this.pageNum + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNum > 1) {
			this.prev = this.pageNum - 1;
		} else {
			this.prev = this.first;
		}
		
		//2
		if (this.pageNum < this.first) {// 如果当前页小于首页
			this.pageNum = this.first;
		}

		if (this.pageNum > this.last) {// 如果当前页大于尾页
			this.pageNum = this.last;
		}
		
	}
	
//	/**
//	 * 获取分页HTML代码
//	 * @return
//	 */
//	public String getHtml(){
//		return toString();
//	}
	
//	public static void main(String[] args) {
//		Page<String> p = new Page<String>(3, 3);
//		System.out.println(p);
//		System.out.println("首页："+p.getFirst());
//		System.out.println("尾页："+p.getLast());
//		System.out.println("上页："+p.getPrev());
//		System.out.println("下页："+p.getNext());
//	}

	/**
	 * 获取设置总数
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置数据总数
	 * @param count
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
//		if (pageSize >= totalCount){
//			pageNum = 1;
//		}
	}
	
	/**
	 * 获取当前页码
	 * @return
	 */
	public int getPageNum() {
		return pageNum;
	}
	
	/**
	 * 设置当前页码
	 * @param pageNum
	 */
	public Criteria<V> setPageNum(int pageNum) {
		this.pageNum = pageNum;
		return this;
	}
	
	/**
	 * 获取页面大小
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 * @param pageSize
	 */
	public Criteria<V> setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * 首页索引
	 * @return
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * 尾页索引
	 * @return
	 */
	public int getLast() {
		return last;
	}

	/**
	 * 获取页面总数
	 * @return getLast();
	 */
	public int getTotalPage() {
		return getLast();
	}

	/**
	 * 是否为第一页
	 * @return
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * 是否为最后一页
	 * @return
	 */
	public boolean isLastPage() {
		return lastPage;
	}
	
	/**
	 * 上一页索引值
	 * @return
	 */
	public int getPrev() {
		if (isFirstPage()) {
			return pageNum;
		} else {
			return pageNum - 1;
		}
	}

	/**
	 * 下一页索引值
	 * @return
	 */
	public int getNext() {
		if (isLastPage()) {
			return pageNum;
		} else {
			return pageNum + 1;
		}
	}
	
	/**
	 * 获取本页数据对象列表
	 * @return List<T>
	 */
	public List<V> getResult() {
		return result;
	}

	/**
	 * 设置本页数据对象列表
	 * @param result
	 */
	public Criteria<V> setResult(List<V> result) {
		this.result = result;
		initialize();
		return this;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public Criteria<V> setParam(Map<String, Object> param) {
		this.param = param;
		return this;
	}

	public Criteria<V> addParam(String key, Object value){
		this.param.put(key, value);
		return this;
	}

	/**
	 * 获取查询排序字符串
	 * @return
	 */
	public String getOrderBy() {
		// SQL过滤，防止注入 
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
					+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(orderBy).find()) {
			return "";
		}
		return orderBy;
	}

	/**
	 * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
	 */
	public Criteria<V> setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult(){
		int firstResult = (getPageNum() - 1) * getPageSize();
		if (firstResult >= getTotalCount()) {
			firstResult = 0;
		}
		return firstResult;
	}
	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults(){
		return getPageSize();
	}

	public long getHowMuchCount() {
		return howMuchCount;
	}

	public void setHowMuchCount(long howMuchCount) {
		this.howMuchCount = howMuchCount;
	}


}
