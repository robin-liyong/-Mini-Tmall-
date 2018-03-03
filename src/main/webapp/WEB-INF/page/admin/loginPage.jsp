<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/res/js/admin/admin_login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/admin/admin_login.css"/>
    <!--[if IE 8]>
    <style type="text/css" rel="stylesheet">
        #div_nav{
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#66000000,endColorstr=#66000000);
        }
        #div_nav>#txt_date,#div_nav>#txt_peel{
            color:#ffffff;
        }
        #div_main>#div_content{
            border-radius: 5px;
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#7F000000,endColorstr=#7F000000);
        }
    </style>
    <![endif]-->
    <title>Tmall 管理后台 - 登录</title>
</head>
<body>
<div id="div_background">
    <div id="div_nav">
        <span id="txt_date"></span>
        <span id="txt_peel">换肤</span>
        <ul id="div_peelPanel">
            <li value="url(${pageContext.request.contextPath}/res/images/admin/loginPage/background-1.jpg)">
                <img src="${pageContext.request.contextPath}/res/images/admin/loginPage/background-mini-1.jpg"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/res/images/admin/loginPage/background-2.jpg)">
                <img src="${pageContext.request.contextPath}/res/images/admin/loginPage/background-mini-2.jpg"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/res/images/admin/loginPage/background-3.jpg)">
                <img src="${pageContext.request.contextPath}/res/images/admin/loginPage/background-mini-3.jpg"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/res/images/admin/loginPage/background-4.jpg)">
                <img src="${pageContext.request.contextPath}/res/images/admin/loginPage/background-mini-4.jpg"/>
            </li>
            <li value="url(${pageContext.request.contextPath}/res/images/admin/loginPage/background-5.jpg)">
                <img src="${pageContext.request.contextPath}/res/images/admin/loginPage/background-mini-5.jpg"/>
            </li>
        </ul>
    </div>
    <div id="div_main">
        <div id="div_head"><p>tmall <span>管理后台</span></p></div>
        <div id="div_content">
            <img id="img_profile_picture" src="${pageContext.request.contextPath}/res/images/admin/loginPage/default_profile_picture-128x128.png"/>
            <input type="text" class="form-control" placeholder="用户名" id="input_username"/>
            <input type="password" class="form-control" placeholder="密码" id="input_password">
            <span id="txt_error_msg">用户名或密码错误</span>
            <input type="button" class="btn btn-danger" id="btn_login" value="登录"/>
        </div>
    </div>
</div>
</body>