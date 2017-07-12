package org.frame.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageBar implements Serializable {

	private static final long serialVersionUID = 8070566027198912646L;
	private long totalPageNum = 0;// 共有页面数目
	private long currentPageNum = 1;// 当前现实的页面数
	private List<Object> resultList = new ArrayList<Object>();// 结果
	private long everyPageNum = 10;// 每页显示数据的条
	private long totalNum = 0;// 共有数据数目
	private Map<String, String> paraMap = null;
	private Object otherObject;
	private String url = "";
  
	public PageBar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageBar(Map<String,String> para){
		this.setCurrentPageNum(para.get("currentPageNum"));
		this.setEveryPageNum(para.get("everyPageNum"));
	}
	
	public void initPara(Map<String,String> para){
		this.setCurrentPageNum(para.get("currentPageNum"));
		this.setEveryPageNum(para.get("everyPageNum"));
	}
	
	public long getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(String currentPageNum) {
		if (null == currentPageNum || "".equals(currentPageNum))
			currentPageNum = "1";
		this.currentPageNum = Long.parseLong(currentPageNum);
	}

	public long getEveryPageNum() {
		return everyPageNum;
	}

	public void setEveryPageNum(String everyPageNum) {
		if (null == everyPageNum || "".equals(everyPageNum))
			everyPageNum = "10";
		this.everyPageNum = Long.parseLong(everyPageNum);
		;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public long getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(long totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
		this.totalPageNum = totalNum / everyPageNum < (double) totalNum
				/ everyPageNum ? ((totalNum / everyPageNum) + 1) : totalNum
				/ everyPageNum;
	}

	public Map<String, String> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getOtherObject() {
		return otherObject;
	}

	public void setOtherObject(Object otherObject) {
		this.otherObject = otherObject;
	}
}