package org.frame.common.utils;

class HeaderNode {
	
	 private String index;  
     private String headerName;  
     private String level;  
     private String parentIndex;  
     private int allOverNodeCount;  
     boolean isoverNode;  
     private int frontCount;  
     public HeaderNode(String headerName,String index,  String level,  
             String parentIndex, int allOverNodeCount, boolean isoverNode,int frontCount) {  
         super();  
         this.index = index;  
         this.headerName = headerName;  
         this.level = level;  
         this.parentIndex = parentIndex;  
         this.allOverNodeCount = allOverNodeCount;  
         this.isoverNode = isoverNode;  
         this.frontCount = frontCount;  
     }  
     public String getIndex() {  
         return index;  
     }  
     public void setIndex(String index) {  
         this.index = index;  
     }  
     public String getHeaderName() {  
         return headerName;  
     }  
     public void setHeaderName(String headerName) {  
         this.headerName = headerName;  
     }  
     public String getLevel() {  
         return level;  
     }  
     public void setLevel(String level) {  
         this.level = level;  
     }  
     public String getParentIndex() {  
         return parentIndex;  
     }  
     public void setParentIndex(String parentIndex) {  
         this.parentIndex = parentIndex;  
     }  
     public int getAllOverNodeCount() {  
         return allOverNodeCount;  
     }  
     public void setAllOverNodeCount(int allOverNodeCount) {  
         this.allOverNodeCount = allOverNodeCount;  
     }  
     public boolean isIsoverNode() {  
         return isoverNode;  
     }  
     public void setIsoverNode(boolean isoverNode) {  
         this.isoverNode = isoverNode;  
     }  
     public int getFrontCount() {  
         return frontCount;  
     }  
     public void setFrontCount(int frontCount) {  
         this.frontCount = frontCount;  
     }  
}
