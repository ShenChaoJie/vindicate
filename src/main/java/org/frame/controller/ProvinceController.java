package org.frame.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import net.sf.json.JSONArray;

import org.frame.common.page.PageBar;
import org.frame.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/province")
public @Data class ProvinceController{
	
	@Autowired
	private ProvinceService provinceService;
	
	@RequestMapping("/toQueryProvince.htm")
	public String toQueryProvince(Model model){
		System.out.println("--------ToQueryProvince");
		return "province";
	}
	
	@RequestMapping("/queryProvince.htm")
	@ResponseBody
	public void queryProvince(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> para = new HashMap<String,String>();
		
		//request.getParameterMap() 转换成为正常使用的 Map<String,String>
		for(Object key:request.getParameterMap().keySet()){
			 Object valueObj = request.getParameterMap().get(key);
			 String value = "";
			 if(null == valueObj){
				  value = "";
		     }else if(valueObj instanceof String[]){
			      String[] values = (String[])valueObj;
			      for(int i=0;i<values.length;i++){
			    	  value = values[i] + ",";
			      }
			      value = value.substring(0, value.length()-1);
		     }else{
		    	  value = valueObj.toString();
		     }
			
			para.put(key+"",value);
		}
		PageBar page = provinceService.queryProvince(para);
		try {
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter responseStream = response.getWriter();
			responseStream.println(JSONArray.fromObject(page));
			responseStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return page.toString();
	}
	
	
}
