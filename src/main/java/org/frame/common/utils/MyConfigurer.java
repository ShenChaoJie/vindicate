package org.frame.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

public class MyConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer{
    
	private static Map<String,String> cmap = new HashMap<String,String>();
	
	public void setMyLocations(Resource[] myLocations){
		java.util.Properties[] ppa = new java.util.Properties[myLocations.length];
		int ppaIndex=0;
		for(Resource rs:myLocations){
    		try {
				java.util.Properties pp = new java.util.Properties();
				pp.load(rs.getInputStream());
				List<String> removeKey = new ArrayList<String>();
				String key ="";
			    for(Iterator it=pp.keySet().iterator();it.hasNext();){
			    	key=it.next().toString();
			    	if(null!=pp.get(key+"/")&&File.separator.equals("/")){
			    		cmap.put(key, pp.get(key+"/").toString());
			    		removeKey.add(key);	
			    	}else{
			    		cmap.put(key, pp.get(key).toString());
			    	}
			    }
			    if(removeKey.size()>0){
			    	for(String rk:removeKey){
			    		if(File.separator.equals("/")){//linux
			    			pp.setProperty(rk, pp.getProperty(rk+"/"));
			    		}
			    	}
				    java.io.OutputStream out=new java.io.FileOutputStream(rs.getFile());			    
				    pp.store(out, "xxx");
				    out.close();
			    }
			    ppa[ppaIndex]=pp;
			    ppaIndex++;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
		this.setPropertiesArray(ppa);
		this.setLocalOverride(true);//覆盖以前的配�?		this.setLocations(myLocations);
	}
	
	public static String getEvn(String key) {
		return cmap.get(key);
	}
	
}
