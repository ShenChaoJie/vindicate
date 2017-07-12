<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=utf-8"%>
<meta charset="utf-8"> 
<title>Bootstrap 实例 - 基本表单</title>
<link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>

<!-- <form role="form">
	<div class="form-group">
		<label for="name">名称</label>
		<input type="text" class="form-control" id="name" 
			   placeholder="请输入名称">
	</div>
	
	<div class="form-group">
		<label for="inputfile">文件输入</label>
		<input type="file" id="inputfile">
		<p class="help-block">这里是块级帮助文本的实例。</p>
	</div>
	<div class="checkbox">
		<label>
			<input type="checkbox"> 请打勾
		</label>
	</div>
	<button type="submit" class="btn btn-default">提交</button>
</form> -->

<div class="form-bottom">
   <form role="form" action="${pageContext.request.contextPath}/login.htm" method="post" class="login-form">
   	<div class="form-group">
   		<label class="sr-only" for="form-username">Username</label>
       	<input type="text" name="lname" placeholder="Username..." class="form-username form-control" id="form-username">
       </div>
       <div class="form-group">
       	<label class="sr-only" for="form-password">Password</label>
       	<input type="password" name="lps" placeholder="Password..." class="form-password form-control" id="form-password">
       </div>
       <button type="submit" class="btn">提交</button>
   </form>
  </div>

	
</body>
</html>