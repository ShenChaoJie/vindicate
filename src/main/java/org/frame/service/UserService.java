package org.frame.service;

import org.frame.persistence.model.TUser;

public interface UserService {

	public TUser login(String username,String password);
	
}
