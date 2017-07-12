package org.frame.persistence.model;

import java.io.Serializable;

import lombok.Data;

public @Data class TRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5239875142067855461L;
	private Integer id;
	private String roleName;
	private String roleType;
	
	
	public TRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TRole(Integer id, String roleName, String roleType) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleType = roleType;
	}
	
	
	
	
}
