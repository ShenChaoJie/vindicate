<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<meta charset="utf-8">
<title>Bootstrap 实例 - 基本的列表组</title>
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/static/css/fileinput-4.3.1.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/static/js/fileinput-4.3.1.js"></script>
<script src="${pageContext.request.contextPath}/static/js/fileinput_locale_zh-4.3.1.js"></script>


<script type="text/javascript">
	$(function() {
		ajaxPost("${pageContext.request.contextPath}/province/queryProvince.htm",backQuery);
	});

	function backQuery(data) {
		var html = "";
		$("#resultData").html("");
		var result = data[0]['resultList'];
		for (var i = 0; i < result.length; i++) {
			html += "<li class='list-group-item'>" + result[i]['name']
					+ "</li>";
		}
		$("#resultData").html(html);
		//ajaxPage(data);
	}

	function ajaxPost(_url, _method, _para) {
		if (_para == null)
			_para = $(":input").serializeArray();
		$.ajax({
			type : "post",
			url : _url,
			data : _para,
			success : function(data) {
				if (data != null && data[0] != null) {
					if (data[0]['info'] == 'sqlEx') {
						alert("数据库操作出错");
					} else if (data[0]['info'] == 'fileEx') {
						alert("文件读取出错");
					} else if (data[0]['info'] == 'otherEx') {
						alert("其他不明错误");
					} else {
						_method.call(this, data);
					}
				} else {
					_method.call(this, data);
				}
			},
			error : ajaxError,
			timeout : 120000,
			dataType : "json"
		});
	}
	function ajaxError() {
		alert("操作出错");
	}
</script>
<!-- <style type="text/css">
.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style> -->
</head>
<body>
	<%-- <shiro:principal /> --%>
	<shiro:principal />
	<shiro:principal type="org.frame.shiro.ShiroUser" property="username" />
	<%-- ${user.username} --%>
	<ul id="resultData" class="list-group">
		<!-- <li class="list-group-item">免费域名注册</li>
	<li class="list-group-item">免费 Window 空间托管</li>
	<li class="list-group-item">图像的数量</li>
	<li class="list-group-item">24*7 支持</li>
	<li class="list-group-item">每年更新成本</li> -->
	</ul>
<br/>

 <table width="100%" border="2" >
      <tbody>
         <tr>
           <td align="center" colspan="1" rowspan="3">ID</td ><!-- colspan="2" rowspan="2"  -->
           <td  align="center" colspan="4" rowspan="1">省份</td >
           <td  align="center" colspan="1" rowspan="3">操作</td>
         </tr>
         <tr>
           <td align="center" colspan="1" rowspan="2">第二行操作</td>
           <td align="center" colspan="3" rowspan="1">第二行ID</td>
         </tr>
         <tr>
           <td align="center" colspan="1" rowspan="1">第三行A</td>
           <td align="center" colspan="1" rowspan="1">第三行B</td>
           <td align="center" colspan="1" rowspan="1">第三行C</td>
         </tr>
       </tbody>
 </table>
 <table>
  <tr>
  	<td>
  		 <span style="position:absolute;border:1pt solid #c1c1c1;overflow:hidden;width:188px;height:19px;clip:rect(-1px 190px 190px 170px);">  
       <select name="aabb" id="aabb" style="width:190px;height:25px;margin:-2px;" onChange="changeF();">  
	       <option value="闲人书库">闲人书库</option>  
	       <option value="闲人BLOG">闲人BLOG</option>  
	       <option value="<a href="http://www.ayinsky.com" target="_blank">闲人设计</a>"><a href="http://www.ayinsky.com" target="_blank">闲人设计</a></option>  
	       <option value="闲人软件">闲人软件</option>  
       </select>  
       <input type="hidden" name="" value=""/>
   </span>  
   <span style="position:absolute;border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:19px;">  
       <input type="text" name="ccdd" id="ccdd" value="请选择或输入..." style="width:170px;height:20px;border:0pt;">  
   </span> 
  	
  	</td>
  	<td>
  		 <span style="position:absolute;border:1pt solid #c1c1c1;overflow:hidden;width:188px;height:19px;clip:rect(-1px 190px 190px 170px);">  
       <select name="aabb" id="aabb" style="width:190px;height:25px;margin:-2px;" onChange="changeF();">  
	       <option value="闲人书库">闲人书库</option>  
	       <option value="闲人BLOG">闲人BLOG</option>  
	       <option value="<a href="http://www.ayinsky.com" target="_blank">闲人设计</a>"><a href="http://www.ayinsky.com" target="_blank">闲人设计</a></option>  
	       <option value="闲人软件">闲人软件</option>  
       </select>  
       <input type="hidden" name="" value=""/>
   </span>  
   <span style="position:absolute;border-top:1pt solid #c1c1c1;border-left:1pt solid #c1c1c1;border-bottom:1pt solid #c1c1c1;width:170px;height:19px;">  
       <input type="text" name="ccdd" id="ccdd" value="请选择或输111入..." style="width:170px;height:20px;border:0pt;">  
   </span>  
  	
  	</td>
  </tr>
 </table>
 
   
  
 

<!-- <div id="select01" class="selectbox">
	<div class="cartes">
		<input type="text" value="jQuery" class="listTxt">
		<div class="listBtn"><b></b></div>
		<input type="hidden" value="v4" class="listVal">
	</div>
	<div class="lists">
		<ul class="list">
			<li id="v1" class="cwhite">jQuery</li>
			<li id="v2" class="cwhite">JavaScript</li>
			<li id="v3" class="cwhite">Prototype</li>
			<li id="v4" class="cwhite">MooTools</li>
			<li id="v5" class="cwhite">Modernizr</li>
			<li id="v6">Kissy</li>
			<li id="v7">YUI</li>
			<li id="v8">其它</li>
		</ul>
	</div>
</div>
 -->

<!-- <div class="btn-group open">
	<button class="btn btn-default" contenteditable="true">Action</button>
	<button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
	<span class="caret"></span>
	</button>
	<ul class="dropdown-menu" contenteditable="true">
	<li>
	<a href="#">操作</a>
	</li>
	<li class="disabled">
	<a href="#">另一操作</a>
	</li>
	<li class="divider"></li>
	<li>
	<a href="#">其它</a>
	</li>
	</ul>
</div>
 -->


<form enctype="multipart/form-data">
        <!-- <input id="kv-explorer" type="file" multiple> -->
        <br>
        <input id="file-0a" class="file" type="file" name="file" multiple data-min-file-count="1" >
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-default">Reset</button>
    </form>

<input type="file" name="image" class="projectfile" value="${deal.image}"/>

</body>
</html>