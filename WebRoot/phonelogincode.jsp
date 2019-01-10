<%--
  Created by IntelliJ IDEA.
  User: fcsca
  Date: 2019-01-10/0010
  Time: 下午 12:34
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
    <title>手机登录</title>
</head>
<body background="<%=basePath%>/img/bg_2.webp" size="100% 80px">
<div align="center">
    <form action="<%=basePath%>getRandomCode"
          method="post"
          style="background: #20c997;"
          size="50px 15px">
        <table>
            <tr>
                <td>
                    手机号
                </td>
                <td>
                    <input type="text" name="user.phone" value=${user.phone}>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="获取验证码">
                </td>
                <td>
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    <div style="color: red;">${msg}</div>
</div>
</body>
</html>
