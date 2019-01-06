<%--
  Created by IntelliJ IDEA.
  User: fcsca
  Date: 2019-01-03/0003
  Time: 下午 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Login</title>
</head>
<body background="<%=basePath%>/img/bg_2.webp" size="100% 80px">
<div align="center">
    <form action="<%=basePath%>/login"
          method="post"
          style="background: #20c997"
          size="50px 15px">
        <table>
            <tr>
                <td>
                    用户名
                </td>
                <td>
                    <input type="text" name="user.name">
                </td>
            </tr>
            <tr>
                <td>
                    密&nbsp码
                </td>
                <td>
                    <input type="password" name="user.password">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="登录">
                </td>
                <td>
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    <div style="red;">${msg}</div>
</div>
</body>
</html>
