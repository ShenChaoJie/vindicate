package org.frame.persistence;

import java.util.Set;

public interface TPermissionMapper {

	 public Set<String> findPermissionByUserId(Integer id);
}
