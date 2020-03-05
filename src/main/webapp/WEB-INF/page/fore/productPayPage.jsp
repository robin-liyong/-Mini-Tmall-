<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/res/js/fore/fore_reward.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_orderPay.css" rel="stylesheet"/>
    <title>天猫tmall.com - 网上支付</title>
    <style type="text/css">
        .frm_label{
            cursor: pointer;
            font-size: 12px;
            font-weight: normal;
            color:#666;
            margin: 0 4px 0 0;
        }
        .frm_input{
            width: 150px;
            outline: none;
            padding: 5px;
            color:#666;
            margin: 0 20px 0 0;
            height: 20px;
            border-radius: 3px;
            border: 1px solid #e9ebef;
            font-size: 12px;
            margin-top: 10px;
        }
        .frm_input:focus{
            border: 1px solid #bbb;
        }
        .frm_btn{
            transition: background-color 0.25s ease-in;
            outline: none;
            height: 20px;
            border-radius: 5px;
            padding: 8px 18px;
            border: 0;
            color:white;
            font-size: 12px;
            background-color: #ff7874;
            margin-right: 10px;
            margin-top: 10px;
        }
        .frm_btn:hover{
            background-color: #fe5874;
        }
        .messageForm {
            width: 300px;
            margin: 0 auto;
            text-align: left;
            margin-top: 10px;
        }
        /*修改多选,单选按钮样式*/
        input[type="checkbox"]+label::before {
            content: "\a0";
            position: relative;
            bottom: 1px;
            display: inline-block;
            vertical-align: middle;
            font-size: 18px;
            width: 12px;
            height: 12px;
            margin-right: .4em;
            border: 1px solid #cccccc;
            text-indent: .15em;
            line-height: 1;
            cursor: pointer;
        }
        input[type="checkbox"]:checked + label::before {
            background-color: #ff7874;
            background-clip: content-box;
            padding: 2px;
        }
        input[type="checkbox"] {
            position: absolute;
            clip: rect(0, 0, 0, 0);
        }
    </style>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}"><img
                    src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/tmallLogoA.png"></a>
        </div>
    </div>
</nav>
<div class="content">
    <div class="order_div">
        <c:choose>
            <c:when test="${fn:length(requestScope.productOrder.productOrderItemList)==1}">
                <div class="order_name">
                    <span>天猫Tmall -- ${requestScope.productOrder.productOrderItemList[0].productOrderItem_product.product_name}</span>
                </div>
                <div class="order_shop_name">
                    <span>卖家昵称：天猫${requestScope.productOrder.productOrderItemList[0].productOrderItem_product.product_category.category_name}旗舰店</span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="order_name">
                    <span>天猫Tmall -- 合并订单：${fn:length(requestScope.productOrder.productOrderItemList)}笔</span>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="order_price">
            <span class="price_value">${requestScope.orderTotalPrice}</span>
            元
            <span class="price_unit">（不会真实付款）</span>
        </div>
    </div>
    <div class="order_pay_div">
        <script>
            function pay() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/order/pay/${requestScope.productOrder.productOrder_code}",
                    type: "PUT",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("订单处理异常，请稍候再试！");
                        }
                        location.href = "/tmall" + data.url;
                    },
                    beforeSend: function () {

                    },
                    error: function () {
                        alert("订单支付出现问题，请重新支付！");
                        location.href = "/tmall/order/0/10";
                    }
                });
            }
        </script>
        <a class="order_pay_btn" href="javascript:void(0)" onclick="pay()">确认支付</a>
    </div>
    <div class="order_reward_div">
        <p class="order_reward_header">作者的话</p>
        <br/>
        <p>首先感谢您看到这里</p>
        <p>本项目是我和其他两个朋友空闲时间在校合作完成的一个商城demo</p>
        <p>前后台业务代码，都是自主完成，后台都是专门设计的样式，前台是参考天猫自行开发界面</p>
        <p>技术日新月异，目前我们在使用更流行的前后台技术栈，从而开源更多优质项目</p>
        <p>但我们仍记得，我们对这个项目付出的热情和精力</p>
        <p>在校开发不易，如对您有帮助，您可以打赏我们</p>
        <p>您打赏后，可以留下您的付款昵称以便我们录入打赏榜，我们会对您的信息进行隐私处理，对您衷心表示感谢</p>
        <form method="post" class="messageForm">
            <label for="name" class="frm_label">昵称</label>
            <input type="text" name="name" id="name" class="frm_input" maxlength="12">
            <input id="checkbox_is_anonymous" name="checkbox_is_anonymous" type="checkbox" value="0">
            <label class="frm_label" id="lbl_checkbox_is_anonymous" for="checkbox_is_anonymous">匿名</label>
            <br/>
            <label for="message" class="frm_label">留言</label>
            <input type="text" name="message" id="message" class="frm_input" maxlength="100">
            <input type="submit" class="frm_btn" id="reward_submit" value="提 交">
        </form>
        <br/>
        <div class="order_reward_alipay_div">
            <p class="order_reward_name" id="reward_alipay_name">支付宝</p>
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/alipay.jpg"/>
        </div>
        <div class="order_reward_weixinpay_div">
            <p class="order_reward_name" id="reward_weixin_name">微信</p>
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/weixinpay.png">
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>
