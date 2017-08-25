package org.frame.persistence;

import java.util.List;
import java.util.Map;

public interface DictMapper {
	
	List<Map<String,Object>> findByDictTitleOrderByDictValue(String dictType);
}
