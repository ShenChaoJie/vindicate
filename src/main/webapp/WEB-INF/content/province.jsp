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
<script type="text/javascript">
	$(function() {
		ajaxPost("${pageContext.request.contextPath}/province/queryProvince.htm",backQuery);
	});

	function backQuery(data) {
		alert(data);
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

</body>
</html>