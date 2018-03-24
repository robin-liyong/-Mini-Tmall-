<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table_normal" id="table_productOrder_list">
    <thead>
    <tr>
        <th>订单号</th>
        <th>邮政编码</th>
        <th>收货人</th>
        <th>联系方式</th>
        <th>留言</th>
        <th>订单状态</th>
        <th>订单详情</th>
        <th hidden>订单ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.productOrderList}" var="productOrder">
        <tr>
            <td>${productOrder.productOrder_code}</td>
            <td>${productOrder.productOrder_post}</td>
            <td>${productOrder.productOrder_receiver}</td>
            <td>${productOrder.productOrder_mobile}</td>
            <td>${productOrder.productOrder_userMessage}</td>
            <td>${productOrder.productOrder_status}</td>
            <td><span class="td_special"><a href="#">详情</a></span></td>
            <td hidden>${productOrder.productOrder_id}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
