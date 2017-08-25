String.prototype.endWith=function(str){var reg=new RegExp(str+"$");return reg.test(this);}
String.prototype.startWith=function(str){return (this.substring(0,str.length)==str);}
function strTrim(value){
    if(value==null)return "";
    return value.replace(/(^\s*)|(\s*$)/g, ""); 
}
function isLetterNum (value){
	var LNName = /^[A-Za-z0-9]+$/;
	if(!LNName.test(value)){
		return false;
	}else return true;
}
function isNum(value){
   var regNum = /^[0-9]*$/;
	if(!regNum.test(value)){
		return false;
	}else return true;
}
//正整数
function isPlusNum(value){
   var regNum = /^[1-9]\d*$/;
	if(!regNum.test(value)){
		return false;
	}else return true;	
}
//数字非空
function isNumEmpty(value){
   var regNum = /^[0-9]+$/;
	if(!regNum.test(value)){
		return false;
	}else return true;
}
function isURL(urlString){
    regExp = /(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i;
    if (urlString.match(regExp))return true;
    else return false;        
}
function isEmail(value){
      var regTextEmail = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/;
      if(!regTextEmail.test(value)){
		  return false;
	   }else return true;   
}
function isDate(dateStr){ 
    var datePat = /^(\d{4})(\-)(\d{1,2})(\-)(\d{1,2})$/;
    var matchArray = dateStr.match(datePat);
    if (matchArray == null) return false; 
    var month = matchArray[3];
    var day = matchArray[5]; 
    var year = matchArray[1]; 
    if (month < 1 || month > 12) return false; 
    if (day < 1 || day > 31) return false; 
    if ((month==4 || month==6 || month==9 || month==11) && day==31) return false; 
    if (month == 2){
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); 
        if (day > 29 || (day==29 && !isleap)) return false; 
    } 
    return true;
}
function isPhone(value){
    var regTextfax = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
    if(!regTextfax.test(value)){
			return false;
	  }else return true;
}
function isMobile(value){
	var regTextfax =/^(13[4-9]|15[012789]|18[789]|147)(\d{8})$/;
    if(!regTextfax.test(value)){
			return false;
	  }else return true;
}
function isIP(s){
	  var patrn=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
	  if (!patrn.exec(s)) return false
	  return true
} 
function inputgtzero(obj){
	   if(obj.value=='')return;
	   if(isNaN(obj.value)){
		   obj.value="";
		   return;
	   }
	    obj.value=obj.value.replace(/[^\d]/g,'');
}
function trims(str)      
{          
    var t = str.replace(/(^\s*)|(\s*$)/g, "");   
    t =  t.replace(/(^　*)|(　*$)/g, ""); 
    return t.replace(/(^\s*)|(\s*$)/g, "");     
} 
function formateDate(str,pattern){
	   var tDate=new Date(str);
	   var ftstr="";
	   if(pattern.indexOf("YYYY")!=-1){
	       ftstr+=tDate.getYear();
	   }
	   if(pattern.indexOf("YYYY")==-1&&pattern.indexOf("YY")!=-1){
	       ftstr+=tDate.getYear().subString(2,4);
	   }
	   if(pattern.indexOf("MM")!=-1){
	       ftstr+="-"+(tDate.getMonth()+1);
	   }
	   if(pattern.indexOf("DD")!=-1){
	       ftstr+="-"+tDate.getDate();
	   }
	   if(pattern.indexOf("HH")!=-1){
	       ftstr+=" "+tDate.getHours();
	   }
	   if(pattern.indexOf("mm")!=-1){
	       ftstr+=":"+tDate.getMinutes();
	   }
	   if(pattern.indexOf("SS")!=-1||pattern.indexOf("ss")!=-1){
	       ftstr+=":"+tDate.getSeconds();
	   }
	   return ftstr;
}
function closeMyPop(){
	$("#myPopTip").css("display","none");
	$("#myDivCover").css("display","none");
}
function getDateYYYYMMDD(str){
	   var tDate=new Date(str);
	   return tDate.getYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate();
}
function getNowDate(){
    var tDate=new Date();
    return tDate.getYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate();
}
function getCheckedRadio(radioName){
	var rs =document.getElementsByName(radioName);
	for(var i=0;i<rs.length;i++){
		if(rs[i].checked)return rs[i].value;
	}
}
function drawImage(ImgD,width,height){
	var image=new Image(); 
	var iwidth = width; //允许图片的最大宽度 
	var iheight = height; //允许图片的最大高度 
	image.src=ImgD.src; 
	if(image.width>0 && image.height>0){ 
		flag=true; 
		if(image.width/image.height>= iwidth/iheight){ 
			if(image.width>iwidth){ 
				ImgD.width=iwidth; 
				ImgD.height=(image.height*iwidth)/image.width; 
			}else{ 
				ImgD.width=image.width; 
				ImgD.height=image.height; 
			} 
		} else{ 
			if(image.height>iheight){ 
				ImgD.height=iheight; 
				ImgD.width=(image.width*iheight)/image.height; 
			}else{ 
				ImgD.width=image.width; 
				ImgD.height=image.height; 
			} 
		} 
	} 

}
//文件格式检查 
function checkUploadFile(tagName){
	   if($("#"+tagName).val()!=""){
	       var sufix=$("#"+tagName).val().lastIndexOf(".");
	       var accept=$("#"+tagName).attr("accept");
	       if(sufix==-1){
	            alert("只支持"+accept+"文件");
	            return false;
	           }
	       sufix=$("#"+tagName).val().substring(sufix+1,$("#"+tagName).val().length).toLowerCase();
	       
	       if(accept.indexOf(sufix)==-1){
		    	   alert("只支持"+accept+"文件");
		           return false;
	           }
		   }
	   return true;
}
function getBytesLength(str) {   
	   return str.replace(/[^\x00-\xff]/g, 'xx').length;   
}
function inputGtLength(objId,maxLength,alt){
	   if(getBytesLength($("#"+objId).val())>maxLength*1){
		   alert(""+alt+",中文按照双字节计算");$("#"+objId).focus();return false;
	   }
	   return true;
}
function getPara(){
	return $(":input").serializeArray();
}
function ajaxPost(_url,_method, _para){
	  if(_para==null)_para=	$(":input").serializeArray();
	  $.ajax({
		  type:"post",
		  url:_url,
		  data:_para,
		  success:function (data){
		     if(data!=null&&data[0]!=null){
		    	if(data[0]['info']=='sqlEx'){
		    		 alert("数据库操作出错");
		    	 }else if(data[0]['info']=='fileEx'){
					alert("文件读取出错");		    		 
				}else if(data[0]['info']=='otherEx'){
					alert("其他不明错误");
				}else{
					_method.call(this,data);
				}
		     }else{
		    	 _method.call(this,data);
		     }
	      },
		  error:ajaxError,
		  timeout:120000,
		  dataType:"json"
	  });
}
function ajaxError(){
	alert("操作出错");
}
function divLogin(){
    showDivDetail(contextPath+"/toDivLogin.do",300,200,10,10,"登录");
}