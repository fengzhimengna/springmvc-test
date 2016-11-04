package com.fzm.base.dto;

import java.util.List;

public class ResultPage<T> {

	/**
	 * 结果集
	 */	
	List<T> dataList;
	/**
	 * 页号
	 */
	Integer pageIndex;
	/**
	 * 每页记录数
	 */
	Integer pageSize;
	/**
	 * 总记录数
	 */
	Long totalCount;
	
	public ResultPage() {}
	
	public ResultPage(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated constructor stub
		if(pageSize == null || pageSize == 0){
			this.pageSize = 10; // 默认每页为10条记录
		}
		this.pageIndex = pageIndex;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
