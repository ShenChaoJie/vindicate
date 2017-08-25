package org.frame.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.frame.common.page.PageBar;
import org.frame.common.utils.ExcelUtils;
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
	
	@RequestMapping("/exportExcelHead.htm")
	public void exportExcelHead(HttpServletRequest request,HttpServletResponse response){
		
		OutputStream os = null;
		String fileName = "subListX86_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ ".xls";
		try {
			if (response != null) {
				os = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			} else {
				os = new FileOutputStream(fileName);
			}
			
			// 创建工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
			// 取Sheet1
			HSSFSheet sheet = wb.createSheet("X86虚拟机");
			
			HSSFCellStyle cellStyle = wb.createCellStyle(); // 样式对象     
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直     
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平     
			cellStyle.setWrapText(true);
			
			//ExcelUtils eu = ExcelUtils.getInstance();
			String[][] header = {  
					 {"机构名称","0","1","-1"},
	                 {"卡类型代码","1","1","-1"},
	                 {"卡类型名称","2","1","-1"},  
	                 {"期初库存量","3","1","-1"},  
	                 {"本期入库情况","4","1","-1"},  
	                 {"本期入库小计","5","2","4"},  
	                 {"本期入库明细","6","2","4"},  
	                 {"印刷入库","7","3","6"},  
	                 {"领用入库","8","3","6"},  
	                 {"回收入库","9","3","6"},  
	                 {"本期出库情况","10","1","-1"},  
	                 {"本期出库小计","11","2","10"},  
	                 {"本期出库明细","12","2","10"},  
	                 {"机构/部门下发出库","13","3","12"},   
	                 {"员工下发出库","14","3","12"},  
	                 {"回收提交出库","15","3","12"},  
	                 {"清理出库","16","3","12"},  
	                 {"销毁出库","17","3","12"},  
	                 {"其他方式出库","18","3","12"},
	                 {"剩余库存量","19","1","-1"}}; 
			
			ExcelUtils.createHeader(header, sheet, 0, cellStyle);
			
			
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				os.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping("/tableHead.htm")
	public String tableHead(HttpServletRequest request,HttpServletResponse response){
		
		
		
		return "table_head";
	}
	
	
}
