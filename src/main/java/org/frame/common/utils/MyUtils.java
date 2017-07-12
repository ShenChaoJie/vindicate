package org.frame.common.utils;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author chaojie.shen
 *
 */
public class MyUtils {
	
	private static MyUtils mu = new MyUtils();
	
	private final static Log log = LogFactory.getLog(MyUtils.class);
	
	public static MyUtils getInstance() {
		return mu;
	}
	
	/**
	 * @return 得到工程当前目录
	 */
	public String getRealPath() {
		String path = this.getClass().getResource("").getPath();
		path = path.substring(1, path.indexOf("WEB-INF/"));
		if(File.separator.equals("/")) return "/"+path;
		return path;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public boolean isBlank(String str) {
		return StringUtils.isBlank(str) || "null".equalsIgnoreCase(str);
	}
	
	 /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public  boolean isNullEmpty(Object str) {
 	   return null==str||"".equals(str.toString().trim())||"null".equals(str.toString().trim())?true:false;
    }
    
    /**
	 * 字符串去除空格
	 * 
	 * @param str
	 * @return
	 */
	public String trim(String str) {
		return StringUtils.trim(str);
	}
	
	/**
	 * 字符串开始
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public boolean startsWith(String str, String suffix) {
		StringUtils.startsWith(str, suffix);
		return StringUtils.startsWith(str, suffix);
	}
	
	/**
	 * 字符串结尾
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public boolean endsWith(String str, String suffix) {
		return StringUtils.endsWith(str, suffix);
	}

	
	/**
	 * JSON格式解析
	 * 
	 * @param postJson
	 * @return
	 */
	public static Map<String, String> getPostJson(String postJson) {
		//MyUtils mu = MyUtils.getInstance();
		Map<String, String> rmap = new LinkedHashMap<String, String>();
		if (mu.isBlank(postJson))
			return rmap;
		try {
			postJson = mu.trim(postJson);
			if (mu.startsWith(postJson, "{") && mu.endsWith(postJson, "}")) {
				rmap.putAll(parserJsonObject(postJson));
			} else if (mu.startsWith(postJson, "[{") && mu.endsWith(postJson, "}]")) {
				rmap.putAll(parserJsonArray(postJson));
			}
		} catch (Exception e) {
			log.error("post json error: " + postJson, e);
		}
		return rmap;
	}

	public static Map<String, String> parserJsonObject(String jsonObj) {
		//MyUtil mu = MyUtil.getInstance();
		Map<String, String> rmap = new LinkedHashMap<String, String>();
		if (mu.isBlank(jsonObj))
			return rmap;
		try {
			jsonObj = mu.trim(jsonObj);
			JSONObject jsonMap = JSONObject.fromObject(jsonObj);
			Iterator<String> it = jsonMap.keys();
			while (it.hasNext()) {
				String key = it.next();
				String value = jsonMap.get(key) == null ? "" : jsonMap.get(key).toString();
				if (mu.startsWith(value, "{") && mu.endsWith(value, "}")) {
					rmap.putAll(parserJsonObject(value));
				} else if (mu.startsWith(value, "[{") && mu.endsWith(value, "}]")) {
					rmap.putAll(parserJsonArray(value));
				} else {
					rmap.put(key, value);
				}
			}
		} catch (Exception e) {
			log.error("post json error: " + jsonObj, e);
		}
		return rmap;
	}

	private static Map<String, String> parserJsonArray(String jsonArr) {
		//MyUtil mu = MyUtil.getInstance();
		Map<String, String> rmap = new LinkedHashMap<String, String>();
		if (mu.isBlank(jsonArr))
			return rmap;
		try {
			jsonArr = mu.trim(jsonArr);
			JSONArray jsonList = JSONArray.fromObject(jsonArr);
			for (int i = 0; i < jsonList.size(); i++) {
				String value = jsonList.getString(i);
				if (mu.startsWith(value, "{") && mu.endsWith(value, "}")) {
					rmap.putAll(parserJsonObject(value));
				} else if (mu.startsWith(value, "[{") && mu.endsWith(value, "}]")) {
					rmap.putAll(parserJsonArray(value));
				}
			}
		} catch (Exception e) {
			log.error("post json error: " + jsonArr, e);
		}
		return rmap;
	}
	
	public static void main(String[] args) {
		System.out.println(MyUtils.getInstance().getRealPath());
	}
	
	
	
}
