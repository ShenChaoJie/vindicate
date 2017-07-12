package org.frame.persistence.model;

import java.io.Serializable;

import lombok.Data;

public @Data class TPermission implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5239875142067855461L;
	private Integer id;
	private String url;
	private String name;
	
	public TPermission() {
		super();
	}

	public TPermission(Integer id, String url, String name) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
	}

	
	
	
	
}
