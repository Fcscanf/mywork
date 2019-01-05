<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>User Register</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="SSH">
    <link href="<%=basePath%>bootjs/bootstrap-4.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>bootjs/js/jquery-3.3.1.min.js"></script>

</head>

<body>
	<%--添加user，如果姓名没有冲突，那么就添加到数据库，如果姓名冲突了就不能添加，返回失败页面--%>
	<div class="text-center">
		<form action="add.action" method="post">
		UserName:<input type="text" name="user.name" value="Fc">${msg}<br />
		UserPassword:<input type="password" name="user.password">${passmsg}<br />
		Phone:<input type="text" name="user.phone" value="17826260016"><br />
		Age:<input type="text" name="user.age" value="请输入年龄10-35岁！">${agemsg}<br />
		Address:<input type="text" name="user.address" value="北京市海淀区"><br />
		<input type="submit" value="Register"> <input type="reset" value="Reset">
	</form>
	</div>
	<div class="text-black-50">
		根据姓名查询，若不输入，则查询全部
		<form action="query.action" method="post">
			UserName： <input type="text" name="queryText" value="${searchText }" />
			<input type="submit" value="查询" />
		</form>
	</div>
	<table width="70%" border="1px" align="center" cellpadding="0"
		cellspacing="0">
		<thead>
			<tr bgcolor="#ff0">
				<th width="10%">编号</th>
				<th width="15%">姓名</th>
				<th width="20%">手机号</th>
				<th width="20%">年龄</th>
				<th width="20%">地址</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users }">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.phone}</td>
					<td>${user.age}</td>
					<td>${user.address}</td>
					<td><a href="edit.action?param=0&id=${user.id}">编辑</a> <a
						href="delete.action?id=${user.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
