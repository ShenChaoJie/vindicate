package org.frame.shiro;

import java.io.Serializable;

/**
 * 
 * @author CJSHEN
 *
 */
public class ShiroUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7653458280423721645L;
	public String id;
	public String username;
	public String realname;
	
	public ShiroUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ShiroUser(String id, String username, String realname) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
