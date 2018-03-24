<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table_normal" id="table_user_list">
    <thead>
    <tr>
        <th>用户名</th>
        <th>昵称</th>
        <th>姓名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>用户详情</th>
        <th hidden>用户ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.userList}" var="user">
        <tr>
            <td>${user.user_name}</td>
            <td>${user.user_nickname}</td>
            <td>${user.user_realname}</td>
            <td>${user.user_gender}</td>
            <td>${user.user_birthday}</td>
            <td><span class="td_special"><a href="#">详情</a></span></td>
            <td hidden>${user.user_id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
