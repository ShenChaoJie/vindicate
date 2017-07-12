package org.frame.persistence;

import org.frame.persistence.model.TUser;

public interface TUserMapper {
	
	public TUser getUserByUsername(String username);
	

}
