<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script>
        $(function () {
            //设置订单状态
            var status_index = '${requestScope.order.productOrder_status}';
            switch (status_index) {
                case '0':
                    $("#wait_point_1").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '1':
                    $("#wait_point_2").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '2':
                    $("#wait_point_3").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '3':
                    $("#wait_point_4").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                default:
                    $("#wait_point_1").children(".wait_point_text").addClass('td_special').text("交易关闭").prev("span").text('×');
                    $("#wait_point_1").addClass("wait_point_select");
            }

            /******
             * event
             ******/
        });
    </script>
    <style rel="stylesheet">
        .details_value.details_value_text {
            vertical-align: text-top;
            width: 500px;
        }

        #lbl_order_userMessage {
            vertical-align: text-top;
        }

        #wait {
            width: 600px;
            height: 2px;
            background-color: #ccc;
            border-radius: 5px;
            position: relative;
            margin: 30px 0 0 50px;
        }

        .wait_point {
            position: absolute;
            width: 1.4em;
            height: 1.4em;
            line-height: 1.4em;
            text-align: center;
            border-radius: 50%;
            background: #ccc;
        }

        .wait_point_select {
            background: #FF7874;
        }

        .wait_point > span {
            position: relative;
            top: 1px;
            color: white;
        }

        .wait_point > .wait_point_text {
            font-size: 12px;
            width: 6em;
            margin-left: -25px;
            color: #666;
            margin-top: 3px;
        }

        .details_status_spacial {
            height: 110px;
        }
    </style>
</head>
<body>
<div class="details_div_first">
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_id">订单号</label>
        <span class="details_value" id="span_order_id">${requestScope.order.productOrder_code}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_user">所属用户</label>
        <span class="details_value td_wait"><a id="span_order_user" href="javascript:void(0)"
                                               onclick="getUserPage(${requestScope.order.productOrder_user.user_id})">${requestScope.order.productOrder_user.user_nickname}</a></span>
    </div>
</div>
<div class="details_div">
    <span class="details_title text_info">基本信息</span>
    <input type="hidden" value="${requestScope.order.productOrder_id}" id="details_order_id"/>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_receiver">收货人姓名</label>
        <span class="details_value" id="span_order_receiver">${requestScope.order.productOrder_receiver}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_address">收货地址</label>
        <span class="details_value details_value_noRows"
              id="span_order_address">${requestScope.order.productOrder_detail_address}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_product_title">邮政编码</label>
        <span class="details_value" id="span_order_post">${requestScope.order.productOrder_post}</span>
        <label class="frm_label text_info" id="lbl_order_mobile">联系电话</label>
        <span class="details_value" id="span_order_mobile">${requestScope.order.productOrder_mobile}</span>
    </div>
</div>
<div class="details_div details_status_spacial">
    <span class="details_title text_info">订单状态</span>
    <div id="wait">
        <div class="wait_point" id="wait_point_1" style="top:-0.7em;left: 0;">
            <span>1</span>
            <div class="wait_point_text">等待买家付款</div>
        </div>
        <div class="wait_point" id="wait_point_2" style="top:-0.7em;left: 33%;">
            <span>2</span>
            <div class="wait_point_text">等待卖家发货</div>
        </div>
        <div class="wait_point" id="wait_point_3" style="top:-0.7em;left: 66%;">
            <span>3</span>
            <div class="wait_point_text">等待买家确认</div>
        </div>
        <div class="wait_point" id="wait_point_4" style="top:-0.7em;left: 100%;">
            <span>4</span>
            <div class="wait_point_text">订单交易完成</div>
        </div>
    </div>
</div>
<div class="details_div details_div_last">
    <span class="details_title text_info">订单项信息</span>
    <table>
        <thead>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.order.productOrderItemList}" var="item" varStatus="i">
            <tr></tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<c:if test="${requestScope.order.productOrder_status != 0 && requestScope.order.productOrder_status != 4}">
    <div class="details_div">
        <span class="details_title text_info">流程时间</span>
        <div class="frm_div">
            <label class="frm_label text_info" id="lbl_order_pay_date">支付日期</label>
            <span class="details_value details_value_noRows"
                  id="span_order_pay_date">${requestScope.order.productOrder_pay_date}</span>
        </div>
        <c:if test="${requestScope.order.productOrder_status != 1}">
            <div class="frm_div">
                <label class="frm_label text_info" id="lbl_order_delivery_date">发货日期</label>
                <span class="details_value details_value_noRows"
                      id="span_order_delivery_date">${requestScope.order.productOrder_delivery_date}</span>
            </div>
            <c:if test="${requestScope.order.productOrder_status == 3}">
                <div class="frm_div">
                    <label class="frm_label text_info" id="lbl_order_confirm_date">确认日期</label>
                    <span class="details_value details_value_noRows"
                          id="span_order_confirm_date">${requestScope.order.productOrder_confirm_date}</span>
                </div>
            </c:if>
        </c:if>
    </div>
</c:if>
<div class="details_tools_div">
    <c:if test="${requestScope.order.productOrder_status==1}">
        <input class="frm_btn" id="btn_order_save" type="button" value="发货"/>
    </c:if>
    <input class="frm_btn frm_clear" id="btn_order_cancel" type="button" value="取消"/>
</div>
<div class="loader"></div>
</body>
</html>
