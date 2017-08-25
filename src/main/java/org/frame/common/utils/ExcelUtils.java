package org.frame.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.Region;

public class ExcelUtils {
	
	private static ExcelUtils eu = new ExcelUtils();
	
	public static ExcelUtils getInstance() {
		return eu;
	}

	public ExcelUtils() {
		super();
	}

	public static void createHeader(String[][] header, HSSFSheet sheet,
			int index, HSSFCellStyle cellStyle) {
		// 创建header
		// 获取树深度
		int deep = getDeep(header);
		// 根据树的深度创建行
		List<HSSFRow> headerRows = new ArrayList<HSSFRow>();
		for (int i = 0; i < deep; i++) {
			HSSFRow headerRow = sheet.createRow((short) index + i);
			headerRow.setHeight((short) 400);
			headerRows.add(headerRow);
		}
		List<HeaderNode> headerNodes = arrayToList(header);
		// 获取叶子节点数量，根据此数量创建列
		int allOverNodeCount = getAllOverNodeCount(header);

		// 创建单元格
		for (int i = 0; i < headerNodes.size(); i++) {
			HeaderNode headerNode = headerNodes.get(i);
			int level = Integer.parseInt(headerNode.getLevel());
			int overNodeCount = headerNode.getAllOverNodeCount();
			int frontCount = headerNode.getFrontCount();
			sheet.setColumnWidth((short) frontCount, (short) 7000);
			HSSFCell headerCell = headerRows.get(level - 1).createCell(
					(short) frontCount);
			headerCell.setCellStyle(cellStyle);
			//headerCell.setEncoding(HSSFCell.ENCODING_UTF_16);
			headerCell.setCellValue(new HSSFRichTextString(headerNode
					.getHeaderName()));
			if (headerNode.isoverNode) {
				// 为叶子节点
				// 向下合并单元格
				Region region = new Region();
				region.setRowFrom(index + level - 1);
				region.setRowTo(index + deep - 1);
				region.setColumnFrom((short) frontCount);
				region.setColumnTo((short) frontCount);
				sheet.addMergedRegion(region);
			} else {
				// 为非叶子节点
				// 向右合并单元格
				Region region = new Region();
				region.setRowFrom(index + level - 1);
				region.setRowTo(index + level - 1);
				region.setColumnFrom((short) frontCount);
				region.setColumnTo((short) (frontCount + overNodeCount - 1));
				sheet.addMergedRegion(region);
			}
		}
	}
	
	
	public static List<Map<String,String>> createTableHead(String[][] header) {
		// 创建header
		// 获取树深度
		int deep = getDeep(header);
		
		List<HeaderNode> headerNodes = arrayToList(header);
		// 根据树的深度创建行
		List<Map<String,String>> headList = new ArrayList<Map<String,String>>();
		for (int i = 0; i < deep; i++) {
			Map<String,String> mapTd = new HashMap<String, String>();
			
			// 创建单元格
			for (int j = 0; j < headerNodes.size(); j++) {
				HeaderNode headerNode = headerNodes.get(j);
				int level = Integer.parseInt(headerNode.getLevel());
				int overNodeCount = headerNode.getAllOverNodeCount();
				int frontCount = headerNode.getFrontCount();
				if (headerNode.isoverNode) {
					// 为叶子节点
					// 向下合并单元格
					/*Region region = new Region();
					region.setRowFrom(index + level - 1);
					region.setRowTo(index + deep - 1);
					region.setColumnFrom((short) frontCount);
					region.setColumnTo((short) frontCount);
					*/
					mapTd.put(j+"", headerNode.getHeaderName()+":"+(deep-level+1)+":"+"1");
					
					
				} else {
					// 为非叶子节点
					// 向右合并单元格
					/*Region region = new Region();
					region.setRowFrom(index + level - 1);
					region.setRowTo(index + level - 1);
					region.setColumnFrom((short) frontCount);
					region.setColumnTo((short) (frontCount + overNodeCount - 1));
					sheet.addMergedRegion(region);*/
					
					mapTd.put(j+"", headerNode.getHeaderName()+":"+(level)+":"+overNodeCount);
				}
			}
			
			headList.add(mapTd);
		}
		return headList;
	}
	
	
	public static void main(String[] args) {
		String[][] header = {{"机构名称","0","1","-1"},  
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
		
		//List<HeaderNode> headerNodes = arrayToList(header);
		
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String, String>();
		map1.put("headerName", "机构名称");
		map1.put("index", "0");
		map1.put("level", "1");
		map1.put("parentIndex", "-1");
		
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("headerName", "本期入库情况");
		map2.put("index", "4");
		map2.put("level", "1");
		map2.put("parentIndex", "-1");
		
		Map<String,String> map3 = new HashMap<String, String>();
		map3.put("headerName", "本期入库小计");
		map3.put("index", "5");
		map3.put("level", "2");
		map3.put("parentIndex", "4");
		
		Map<String,String> map4 = new HashMap<String, String>();
		map4.put("headerName", "本期入库明细");
		map4.put("index", "6");
		map4.put("level", "2");
		map4.put("parentIndex", "4");
		
		Map<String,String> map5 = new HashMap<String, String>();
		map5.put("headerName", "印刷入库");
		map5.put("index", "7");
		map5.put("level", "3");
		map5.put("parentIndex", "6");
		
		
		Map<String,String> map6 = new HashMap<String, String>();
		map6.put("headerName", "领用入库");
		map6.put("index", "8");
		map6.put("level", "3");
		map6.put("parentIndex", "6");
		
		Map<String,String> map7 = new HashMap<String, String>();
		map7.put("headerName", "领用入库");
		map7.put("index", "9");
		map7.put("level", "3");
		map7.put("parentIndex", "6");
		
		listMap.add(map1);
		listMap.add(map2);
		listMap.add(map3);
		listMap.add(map4);
		listMap.add(map5);
		listMap.add(map6);
		listMap.add(map7);
		System.out.println(listMapToStrArr(listMap));
		
		
		
		
		//System.out.println(createTableHead(header));
		
	}
	
	
	  /** 
     * 数组转化list集合 
     * @param header 
     * @return 
     */  
    private static List<HeaderNode> arrayToList(String[][] header){  
        List<HeaderNode> headerNodes = new ArrayList<HeaderNode>();  
        for(String[] headernode : header){  
            //获取此节点下的所有叶子节点数量  
            int count = getOverNodeCount(headernode,header);  
            int frontCount = getFrontOverNodeCount(headernode,header);  
            headerNodes.add(new HeaderNode(headernode[0], headernode[1], headernode[2], headernode[3],count,isOverNode(headernode,header),frontCount));  
        }  
        return headerNodes;  
    }  
    
    /** 
     * 获取level层级下的所有表头节点 
     * @param header 
     * @param level 
     * @return 
     */  
    private static List<HeaderNode> arrayToListByLevel(String[][] header,int level){  
        List<HeaderNode> headerNodes = new ArrayList<HeaderNode>();  
        for(String[] headernode : header){  
            if(headernode[2].equals(level+"")){  
                //获取此节点下的所有叶子节点数量  
                int count = getOverNodeCount(headernode,header);  
                int frontCount = getFrontOverNodeCount(headernode,header);  
                headerNodes.add(new HeaderNode(headernode[0], headernode[1], headernode[2], headernode[3],count,isOverNode(headernode,header),frontCount));  
            }  
        }  
        return headerNodes;  
    }  
      
    /** 
     * 获取总叶子节点的数量，根据此数量创建表头列数 
     * @param header 
     * @return 
     */  
    private static int getAllOverNodeCount(String[][] header){  
        int count = 0;  
        //获取表深度  
        int deep = Integer.parseInt(header[header.length-1][2]);  
        for(int i=0;i<header.length;i++){  
            if(isOverNode(header[i], header)){  
                count++;  
            }  
        }  
        return count;  
    }  
      
    /** 
     * 获取当前节点下的所有叶子节点数量 
     * @param headerNode 
     * @param header 
     * @return 
     */  
    private static int getOverNodeCount(String[] headerNode,String[][] header){  
        int count = 0;  
        for(int i=0;i<header.length;i++){  
            if(header[i][3].equals(headerNode[1])){  
                if(isOverNode(header[i], header)){  
                    count++;  
                }else{  
                    count += getOverNodeCount(header[i], header);  
                }  
            }  
        }  
        return count;  
    }  
      
    /** 
     * 判断是否为叶子节点 
     * @param headerNode 
     * @param header 
     * @return 
     */  
    private static boolean isOverNode(String[] headerNode,String[][] header){  
        for(int i=0;i<header.length;i++){  
            if(header[i][3].equals(headerNode[1])){  
                return false;  
            }  
        }  
        return true;  
    }  
      
      
    /** 
     * 获取当前节点空间顺序之前的所有叶子节点数量 
     * @param headerNode 
     * @param header 
     * @return 
     */  
    private static int getFrontOverNodeCount(String[] headerNode,String[][] header){  
        int count = 0 ;  
        for(int i=0;i<header.length;i++){  
            if(header[i].equals(headerNode)){  
                break;  
            }  
            if(isOverNode(header[i], header)){  
                count++;  
            }  
        }  
        return count;  
    }  
      
    private static int getDeep(String[][] header){  
        int deep = 0;  
        for(int i=0;i<header.length;i++){  
            if(Integer.parseInt(header[i][2])>deep){  
                deep = Integer.parseInt(header[i][2]);  
            }  
        }  
        return deep;  
    } 
    
    
    public static String[][] listMapToStrArr(List<Map<String,String>> listMap){
    	String[][] aa  = new String[listMap.size()][4];
    	
    	for(int i=0;i<listMap.size();i++){
    		aa[i][0] = listMap.get(i).get("headerName");
    		aa[i][1] = listMap.get(i).get("index");
    		aa[i][2] = listMap.get(i).get("level");
    		aa[i][3] = listMap.get(i).get("parentIndex");
    	}
		return aa;
    }
    
}
