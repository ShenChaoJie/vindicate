<html>
<head>
<%@ include file="header.jsp"%>
<%@ page contentType="text/html; charset=utf-8"%>
<title>xx</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

</head>
<body>
<div class="main">
<div class="nav">省份配置管理</div>
<div class="selectthings">
	<table>
     <tr>
       <td width="100%">
   		<input type="button" value="添加" onclick="add_()"  class="btn">&nbsp;&nbsp;&nbsp;&nbsp;
       </td>
     </tr>
   </table>
</div>
<div class="qdata">
    <table width="100%" class="datatable" >
      <tbody>
         <tr>
           <th width="30%">ID</th>
           <th width="30%">省份</th>
           <th width="50%">操作</th>
         </tr>
       </tbody>
        <tbody id="resultData">
       </tbody>
    </table>
	<script type='text/javascript' src='${pageContext.request.contextPath}/js/ajaxPage.js'></script>
</div>
</div>  
</body>
</html>