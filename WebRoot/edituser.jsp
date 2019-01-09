<%--
  Created by IntelliJ IDEA.
  User: fcsca
  Date: 2019-01-09/0009
  Time: 上午 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>EditUser</title>
</head>
<body>
编辑User
<form action="edit.action" method="post">
    ID:<input type="text" name="user.id" value="${user.id}" readonly="readonly"><br/>
    姓名:<input type="text" name="user.name" value="${user.name}"><br/>
    手机:<input type="text" name="user.phone" value="${user.phone}"><br/>
    年龄:<input type="text" name="user.age" value="${user.age}"><br/>
    地址:<input type="text" name="user.address" value="${user.address}"><br/>
    <input type="hidden" name="param" value="1"/>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
</form>
</body>
</html>
