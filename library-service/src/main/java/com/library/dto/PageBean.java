package com.library.dto;

import java.util.List;
/**
 * 分页数据由此对象负责传输
 * @author lorenzo
 *
 */
public class PageBean {
	
	private int maxResult = 8;//每页显示记录数(设置)*
	
	private int currentPage;//当前页(验证有效性)*
	
	private int countResult;//总记录数(从数据库获取)*
	
	private int pageCount;//总页数(根据总记录数计算)get
	
	private List<?> list;//存放分页查询的结果集数据*
	
	//++getFirstResult()
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}
	
	/**
	 * 获取当前页
	 * @return
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}
	
	/**
	 * 设置当前页,注意验证数据的有效性
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = (currentPage == 0) ? 1 : currentPage;
	}
	
	/**
	 * 获取每页显示记录数
	 * @return
	 */
	public int getMaxResult() {
		return maxResult;
	}
	
	/**
	 * 设置每页显示记录数
	 * @param maxResult
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	/**
	 * 计算从第几条开始取
	 * @return
	 */
	public int getFirstResult() {
		//当前页数-1*最大显示数
		return (this.getCurrentPage() - 1) * this.maxResult;
	}
	
	/**
	 * 获取当前的分页数据
	 * @return
	 */
	public List<?> getList() {
		return list;
	}
	
	/**
	 * 设置当前分页数据
	 * @param list
	 */
	public void setList(List<?> list) {
		this.list = list;
	}
	
	/**
	 * 设置总记录数，并计算出总页数
	 * @param countResult
	 */
	public void setCountResult(int countResult) {
		this.countResult = countResult;
		this.pageCount = (countResult % maxResult == 0) ? 
				(countResult / maxResult):(countResult / maxResult + 1);
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getCountResult() {
		return countResult;
	}

	@Override
	public String toString() {
		return "PageBean [maxResult=" + maxResult + ", currentPage=" + currentPage + ", countResult=" + countResult
				+ ", pageCount=" + pageCount + ", list=" + list + "]";
	}
	
}
