package org.frame.common.enummodel;

import lombok.Getter;

public enum Season implements SeasonInterface {
	SPRING("001","春天"),
	SUNMER("002","夏天"),
	AUTUMN("003","秋天"),
	WINTER("004","冬天");

	@Getter
	private String code;
	@Getter
	private String name;
	
	Season(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Season.SPRING.getCode());
		System.out.println(Season.SPRING.getName());
	}


	@Override
	public void print() {
		System.out.println(this.getCode()+"_"+this.name);
	}


	@Override
	public String getInfo() {
		return this.getName();
	}
}
