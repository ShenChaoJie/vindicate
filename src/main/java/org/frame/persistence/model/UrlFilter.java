package org.frame.persistence.model;

import java.io.Serializable;

import lombok.Data;

public @Data class UrlFilter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1840031342379715701L;
	private Long id;  
    private String name; //url名称/描述  
    private String url; //地址  
    private String roles; //所需要的角色，可省略  
    private String permissions; //所需要的权限，可省略  
    
    
	public UrlFilter(Long id, String name, String url, String roles,
			String permissions) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.roles = roles;
		this.permissions = permissions;
	}


	public UrlFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}

