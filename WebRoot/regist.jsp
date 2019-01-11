<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <sx:head extraLocales="en"/>
    <title>User Register</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="SSH">
    <link href="<%=basePath%>bootjs/bootstrap-4.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>bootjs/js/jquery-3.3.1.min.js"></script>

</head>

<body background="img/bg_2.webp">
<%--添加user，如果姓名没有冲突，那么就添加到数据库，如6果姓名冲突了就不能添加，返回失败页面--%>
<div style="margin-left: 480px;">
    <div style="margin-right: 10px">
        <a href="phonelogincode.jsp">退出</a>
    </div>
    <figure class="figure" style="margin-left: 130px">
        <h1>盐淘商城网</h1>
    </figure>
    <form action="add.action" method="post">
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <label>昵称</label>
                <input type="text" name="user.name" class="form-control is-valid"
                       value=${user.name}>
                <div class="valid-feedback">
                    请输入您的昵称！<span style="color: red;">${nameMsg}</span>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label>密码</label>
                <input type="password" name="user.password" class="form-control is-valid"
                       maxlength="20">
                <div class="valid-feedback">
                    请输入您的密码，密码必须中英文组合且密码长度至少6不能多于20位！
                    <span style="color: red;">${passMsg}</span>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label>年龄</label>
                <input type="text" name="user.age" class="form-control is-valid"
                       maxlength="2" min="10" max="35"
                       value=${user.age}>
                <div class="valid-feedback">
                    请输入您的年龄，限制10-35岁注册！<span style="color: red;">${ageMsg}</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <label>手机</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend1">Tel</span>
                    </div>
                    <input type="text" name="user.phone" class="form-control is-valid"
                           maxlength="11" value=${user.phone}>
                    <div class="valid-feedback">
                        请输入您的手机号！<span style="color: red;">${phoneMsg}</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label>地址</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrepend3">@</span>
                    </div>
                    <input type="text" name="user.address" class="form-control is-valid"
                           maxlength="20" value=${user.address}>
                    <div class="valid-feedback">
                        请输入您的居住地！
                    </div>
                </div>
            </div>
        </div>
        <div style="margin-left: 150px">
            <input type="submit" class="btn btn-primary" value="Register">
            <input type="reset" class="btn btn-primary" value="Reset">
        </div>
    </form>
</div>
<div class="text-black-50">
    根据姓名查询，若不输入，则查询全部
    <form action="query.action" method="post">
        UserName： <input type="text" name="queryText" value="${searchText }"/>
        <input type="submit" value="查询"/>
    </form>
    查询某一时间段内注册的用户
    <form action="qurt.action" method="post">
        <%--StartTime: <sx:datetimepicker name="startTime" displayFormat="yyyy-MM-dd HH:mm:ss" language="en"></sx:datetimepicker>--%>
        <%--EndTime: <sx:datetimepicker name="endTime" displayFormat="yyyy-MM-dd HH:mm:ss" language="en"></sx:datetimepicker>--%>
            StartTime:<input type="text" name="startTime" value="${startTime}">
            EndTime:<input type="text" name="endTime" value="${endTime}">
        <input type="submit" value="查询"/>
    </form>
</div>
<table width="80%" border="1px" align="center" cellpadding="0"
       cellspacing="0" class="table table-dark">
    <thead>
    <tr>
        <th width="10%" scope="col">编号</th>
        <th width="15%" scope="col">姓名</th>
        <th width="15%" scope="col">手机号</th>
        <th width="10%" scope="col">年龄</th>
        <th width="20%" scope="col">地址</th>
        <th width="20%" scope="col">注册时间</th>
        <th width="10%" scope="col">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users }">
        <tr>
            <td scope="row">${user.id}</td>
            <td>${user.name}</td>
            <td>${user.phone}</td>
            <td>${user.age}</td>
            <td>${user.address}</td>
            <td>${user.registDate}</td>
            <td><a href="edit.action?param=0&id=${user.id}">编辑</a> <a
                    href="delete.action?id=${user.id}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
