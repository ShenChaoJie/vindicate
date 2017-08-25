package org.frame.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.frame.common.utils.StringUtil;
import org.frame.persistence.DictMapper;
import org.frame.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<Map<String, Object>> getDictList(String dictType) {
		return dictMapper.findByDictTitleOrderByDictValue(dictType);
	}

	@Override
	public String getDictValueByName(Object name, String dictType) {
		List<Map<String, Object>> dictMapList = getDictList(dictType);
		Map dictMap = getDictMap(dictMapList, "dict_name", "dict_code");
	    return getDictStr(name.toString(), dictMap);
	}
	
	private Map<String,Object> getDictMap(List<Map<String,Object>> dictMapList, String dictFromFiled, String dictToFiled) {
        return getMapFromList(dictMapList, dictFromFiled, dictToFiled);
    }
	
	private Map<String,Object> getMapFromList(List<Map<String,Object>> dictMapList,String dictFromFiled, String dictToFiled){
		Map<String,Object> retMap = new HashMap<String, Object>();
		for(Map<String,Object> dict:dictMapList){
			retMap.put(dict.get(dictFromFiled).toString(), dict.get(dictToFiled));
		}
		return retMap;
	}
	
	private String getDictStr(String value, Map dictMap, String... nullStrArray) {
        String name = StringUtil.getMapValue(dictMap, value);
        if (StringUtils.isBlank(name) && StringUtil.isNotBlank(nullStrArray)) {
            name = nullStrArray[0];
        }
        return name;
    }

	@Override
	public String getDictNameByValue(Object value, String dictType) {
		List<Map<String, Object>> dictMapList = getDictList(dictType);
		Map dictMap = getDictMap(dictMapList, "dict_code", "dict_name");
	    return getDictStr(value.toString(), dictMap);
	}

	@Override
	public void addDictValueByName(List<Map<String, Object>> list, String dictType, String key,String fieldFix, String... nullStrArray) {
		 List<Map<String, Object>> dictMapList = getDictList(dictType);
         Map dictMap = getDictMap(dictMapList, "dict_name", "dict_code");
         addDict(list, dictMap, key, fieldFix, nullStrArray);
	}
	
	private void addDict(List<Map<String, Object>> list, Map dictMap, String key, String fieldFix, String... nullStrArray) {
        for (Map map : list) {
            addDict(map, dictMap, key, fieldFix, nullStrArray);
        }
    }
	
	private void addDict(Map map, Map dictMap, String key, String fieldFix, String... nullStrArray) {
        String value = StringUtil.getMapValue(map, key);
        String res = getDictStr(value, dictMap, nullStrArray);
        map.put(key + fieldFix, res);
    }

	@Override
	public void addDictNameByValue(List<Map<String,Object>> list, String dictType, String key,String fieldFix, String... nullStrArray) {
		 List<Map<String, Object>> dictMapList = getDictList(dictType);
         Map dictMap = getDictMap(dictMapList, "dict_code", "dict_name");
         addDict(list, dictMap, key, fieldFix, nullStrArray);
	}

}
