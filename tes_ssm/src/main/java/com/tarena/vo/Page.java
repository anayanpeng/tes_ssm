package com.tarena.vo;

import java.util.List;

public class Page {
	private int currentPage;
	private int pageSize;
	private int previousPage;
	private int nextPage;
	private int totalCount;
	private int totalPage;
	
	private int begin;
	
	private List<Integer> aNum;
	
	private String roleKeyword;
	
	private String userKeyword;
	
	private List data;
	
	private String roleType;//用户管理中的角色类型,讲师,学员,管理员

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", previousPage=" + previousPage
				+ ", nextPage=" + nextPage + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", begin="
				+ begin + ", aNum=" + aNum + ", roleKeyword=" + roleKeyword + ", userKeyword=" + userKeyword + ", data="
				+ data + ", roleType=" + roleType + "]";
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		totalPage=(totalCount%pageSize==0)? (totalCount/pageSize) : (totalCount/pageSize)+1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBegin() {
		begin=(currentPage-1)*pageSize;
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public List<Integer> getaNum() {
		return aNum;
	}

	public void setaNum(List<Integer> aNum) {
		this.aNum = aNum;
	}

	public String getRoleKeyword() {
		return roleKeyword;
	}

	public void setRoleKeyword(String roleKeyword) {
		this.roleKeyword = roleKeyword;
	}

	public String getUserKeyword() {
		return userKeyword;
	}

	public void setUserKeyword(String userKeyword) {
		this.userKeyword = userKeyword;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	
	
}
