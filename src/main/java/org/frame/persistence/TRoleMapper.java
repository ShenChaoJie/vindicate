package org.frame.persistence;

import java.util.Set;

public interface TRoleMapper {
	
	public Set<String> findRoleByUserId(Integer userId);

}
