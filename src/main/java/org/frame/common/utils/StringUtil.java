package org.frame.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	
	
}
