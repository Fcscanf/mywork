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

<title>mywork</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="SSH">

</head>

<body>
	添加user，如果姓名没有冲突，那么就添加到数据库，如果姓名冲突了就不能添加，返回失败页面
	<form action="add.action" method="get">
		UserName:<input type="text" name="user.name"><br />
		UserPassword:<input type="password" name="user.password"><br />
		Phone:<input type="text" name="user.phone"><br />
		Age:<input type="text" name="user.age"><br />
		Address:<input type="text" name="user.address"><br />
		<input type="submit" value="提交"> <input type="reset" value="重置">
	</form>
	根据姓名查询，若不输入，则查询全部
	<form action="query.action" method="post">
		UserName： <input type="text" name="queryText" value="${searchText }" />
		<input type="submit" value="查询" />
	</form>

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
