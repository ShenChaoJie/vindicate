package org.frame.persistence.model;

import java.util.Date;

public class TProvince implements java.io.Serializable {

	private static final long serialVersionUID = 7277321030261568800L;
	
	private Integer id;
	private Integer pid;
	private String name;
	private String pname;
	private Date ctime;

	public TProvince() {
		super();
	}
	
	public TProvince(Integer id, Integer pid, String name, String pname,
			Date ctime) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.pname = pname;
		this.ctime = ctime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
