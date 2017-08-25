package org.frame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.frame.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DictControl{

	@Autowired
	private DictService dictService;
	
	@RequestMapping("/dictCtrl.htm")
	public String toQueryProvince(Model model){
		List<Map<String,Object>> dictList = dictService.getDictList("boxSpId");
		
		String  dictName =dictService.getDictNameByValue("3", "boxSpId");
		
		String  dictValue = dictService.getDictValueByName("SOHU百宝箱", "boxSpId");
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("abc", "SOHU百宝箱");
		list.add(map);
		dictService.addDictValueByName(list, "boxSpId", "abc", "_fx");
		
		map.put("def", "3");
		list.add(map);
		dictService.addDictNameByValue(list, "boxSpId", "def", "_fx");
		
		return "province";
	}
	
}
