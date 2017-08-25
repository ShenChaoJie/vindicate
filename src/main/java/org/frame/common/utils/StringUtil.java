package org.frame.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {

	
	public StringUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将字符串中的 占位符"#",替换成值
	 * @param oldString
	 * @param arg
	 * @return
	 */
	public static String replaceString(String oldString, String... arg)
    {
    	for(String replaceStr:arg){
    		oldString = oldString.replaceFirst("#", replaceStr);
    		
    	}
    	return oldString;
    	
    }
	
	public static Map<String,Object> jsonToMap(String jsonStr){
		HashMap<String,Object> result = null;
		try {
		    result = new ObjectMapper().readValue(jsonStr, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getMapValue(Map<String,Object> map,String key){
		Object value = map.get(key);
		if(value!=null){
			return value.toString();
		}
		return null;
	}
	
	public static boolean isNotBlank(String... strArr){
		if(strArr.length>0){
			return true;
		}
		return false;
	}
	
	
	public static String getStringValue(Object obj){
		if(obj==null){
			return "";
		}else{
			return String.valueOf(obj).equals("null")?"":String.valueOf(obj);
		}
	}
	
	public static Integer getIntValue(Object obj){
		if(obj==null){
			return null;
		}else{
			String str = String.valueOf(obj).equals("null")?"":String.valueOf(obj);
			if(!"".equals(str)){
				return Integer.parseInt(str);
			}
		}
		
		return null;
	} 
	
	
	
}
