package org.frame.service;

import java.util.List;
import java.util.Map;

public interface DictService {

	/**
	 * 获取字典列表
	 * @param type
	 * @return
	 */
	public List<Map<String,Object>> getDictList(String dictType);
	
	/**
	 * 格式化 外部dictMapList
	 * @param dictMapList
	 * @param dictFromFiled
	 * @param dictToFiled
	 * @return
	 */
	//public Map<String,Object> getDictMap(List<Map<String,Object>> dictMapList, String dictFromFiled, String dictToFiled);
	
	
	/**
	 * 根据 value 获取name
	 * @param value
	 * @param dictMap
	 * @param nullStrArray
	 * @return
	 */
	//public String getDictStr(String value, Map dictMap, String... nullStrArray);
	
	
	/**
	 * 根据 value 获取 name
	 * @param name
	 * @param dictType
	 * @return
	 */
	public String getDictValueByName(Object name, String dictType);
	
	/**
	 * 根据 name 获取 value
	 * @param value
	 * @param dictType
	 * @return
	 */
	public String getDictNameByValue(Object value, String dictType);
	
	
	
	/**
	 * 将 list中的 字段value 转换成name
	 * @param list
	 * @param dictType
	 * @param key
	 * @param fieldFix
	 * @param nullStrArray
	 */
	public void addDictValueByName(List<Map<String, Object>> list, String dictType, String key,String fieldFix, String... nullStrArray);
	
	
	/**
	 * 
	 * @param list
	 * @param dictType
	 * @param key
	 * @param fieldFix
	 * @param nullStrArray
	 */
	public void addDictNameByValue(List<Map<String, Object>> list, String dictType, String key, String fieldFix, String... nullStrArray);
	
	
	
	
	
	
	
}
