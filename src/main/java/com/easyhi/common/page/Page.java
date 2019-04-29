package com.easyhi.common.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * <p>分页信息。</p>
 * <p>继承ArrayList是因为如果BaseMapper.getPage方法的返回类型是Page，而mybatis有如下判断：</p>
 * <pre>
 * if (List.class.isAssignableFrom(method.getReturnType())) {
 *    returnsList = true;//即只有返回List才执行selectList。
 * }
 * </pre>
 * 
 * @param <T>
 */
public class Page<T> extends ArrayList<T> {
	
	private static final long serialVersionUID = 1L;

	public final static String PAGE_KEY="param_key_page";
	
	public final static int DEFAULT_PAGE_SIZE=10;
	
	/**每页显示几条*/
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/**总条数*/
	private int totalCount = 0; 
	
	/**当前页*/
	private int currentPage = 0; 
	
	/**当前记录起始索引*/
	private int startIndex = 0; 

	/**存放结果集*/
	private Collection<T> results = new ArrayList<T>();

	public Collection<T> getResults() {
		if (results == null) {
			return new ArrayList<T>();
		}
		return results;
	}

	public void setResults(Collection<T> results) {
		this.results = results;
	}

	/**
	 * 
	 * <p>
	 * 获取总页数
	 * </p>
	 * 
	 * @return
	 */
	public int getTotalPage() {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		}
		return totalCount / pageSize + 1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * <p>
	 * 设置总条数
	 * </p>
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		startIndex = (getCurrentPage() - 1) * getPageSize();
		if (startIndex < 0) {
			startIndex = 0;
		}
		return startIndex;
	}

}