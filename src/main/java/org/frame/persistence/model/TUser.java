package org.frame.persistence.model;

import java.util.Date;

import lombok.Data;

public @Data class TUser implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2320352154981018521L;
	private Integer id;
	private String lname;
	private String lpswd;
	private String email;
	private Date ctime;
	private Date lastLoginTime;
	private Integer status;
	
	public TUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TUser(Integer id, String lname, String lpswd, String email,
			Date ctime, Date lastLoginTime, Integer status) {
		super();
		this.id = id;
		this.lname = lname;
		this.lpswd = lpswd;
		this.email = email;
		this.ctime = ctime;
		this.lastLoginTime = lastLoginTime;
		this.status = status;
	}
	
	
	

}
