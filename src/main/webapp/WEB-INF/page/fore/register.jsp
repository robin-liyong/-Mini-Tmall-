<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/res/js/fore/fore_register.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_register.css" rel="stylesheet">
    <title>天猫tmall.com - 网上注册</title>
    <script>
        $(function () {
            $("span.address_province").text($("#select_order_address_province").find("option:selected").text());
            $("span.address_city").text($("#select_order_address_city").find("option:selected").text());
            $("span.address_district").text($("#select_order_address_district").find("option:selected").text());
        })
    </script>
    <style rel="stylesheet">
        #baseNavigator {
            padding: 22px 0;
            width: 1190px;
            height: 44px;
            margin: auto;
        }

        #baseNavigator img {
            width: 190px;
            margin-top: 8px;
        }

        #nav {
            width: auto;
            height: 32px;
            font-family: "Microsoft YaHei UI", Tahoma, serif;
            font-size: 12px;
            position: relative !important;
            background: #f2f2f2;
            z-index: 999;
            border-bottom: 1px solid #e5e5e5;
        }
    </style>
</head>
<body>
<nav id="baseNavigator">
    <a href="${pageContext.request.contextPath}" target="_self">
        <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/tmallLogoA.png"/>
    </a>
</nav>
<div class="content">
    <div class="steps">
        <span class="steps_tsl">填写账户信息</span>
    </div>
    <div class="form-list form-main-list">
        <form action="${pageContext.request.contextPath}/register/toRegister" method="post" id="register_form">
            <div class="form-item">
                <label class="form-label form-label-b tsls">设置会员名</label>
            </div>
            <div class="form-item">
                <label class="form-label tsl">用户名：</label>
                <input name="user_name" id="user_name" class="form-text err-input" placeholder="请输入用户名">
                <span class="form_span"></span>
            </div>
            <div class="form-item">
                <label class="form-label form-label-b tsls">设置登录密码</label>
                <label class="form-label tsl">登录时验证，保护账号信息</label>
            </div>
            <div class="form-item">
                <label class="form-label tsl">登录密码：</label>
                <input name="user_password" type="password" id="user_password" class="form-text err-input" placeholder="请设置登录密码">
                <span class="form_span"></span>
            </div>
            <div class="form-item">
                <label class="form-label tsl">确认密码：</label>
                <input name="user_password_one" type="password"  id="user_password_one" class="form-text err-input" placeholder="请再次输入你的密码">
                <span class="form_span"></span>
            </div>
            <div class="form-item">
                <label class="form-label form-label-b tsls">填写基本信息</label>
            </div>
            <div class="form-item">
                <label class="form-label tsl">昵称：</label>
                <input name="user_nickname" id="user_nickname" class="form-text err-input" placeholder="请输入昵称">
                <span class="form_span"></span>
            </div>
            <div class="form-item">
                <label class="form-label tsl">性别：</label>
                <input name="user_gender" type="radio" id="form_radion" value="0" checked="checked">男&nbsp;
                <input name="user_gender" type="radio" id="form_radions"  value="1" >女
            </div>
            <div class="form-item">
                <label class="form-label tsl">出生日期：</label>
                <input type="date" name="user_birthday" id="user_birthday" class="form-text err-input" placeholder="">
                <span class="form_span"></span>
            </div>
            <div class="form-item">
                <label class="form-label tsl">居住地址：</label>
                <select class="selectpicker" id="select_user_address_province" data-size="8" data-live-search="true">
                    <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                        <option value="${address.address_areaId}"
                                <c:if test="${requestScope.addressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                    </c:forEach>
                </select>
                <select class="selectpicker" id="select_user_address_city" data-size="8" data-live-search="true">
                    <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                        <option value="${address.address_areaId}"
                                <c:if test="${requestScope.cityAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                    </c:forEach>
                </select>
                <select name="user_address" class="selectpicker" id="select_user_address_district" data-size="8" data-live-search="true">
                    <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                        <option value="${address.address_areaId}"
                                <c:if test="${requestScope.districtAddressId==address.address_areaId}">selected</c:if>>${address.address_name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-item">
                <input type="submit" id="register_sub" class="btns btn-large tsl"/>
            </div>
        </form>
    </div>
</div>
<%@include file="include/footer.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/fore/fore_foot_special.css" rel="stylesheet"/>
</body>

