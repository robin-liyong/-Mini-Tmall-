<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/res/js/jquery-color-2.1.2.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/fore/fore_home.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_home.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_nav.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_foot.css" rel="stylesheet"/>
    <title>天猫tmall.com--理想生活上天猫</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/HomeLogoA.png">
        <div class="mallSearch">
            <form action="fore_search.action" method="get">
                <div class="mallSearch-input">
                    <input class="header_search_input" type="text" name="goodsName" placeholder="搜索 天猫 商品/品牌/店铺">
                    <input class="header_search_button" type="submit" value="搜索">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <c:if test="${i.index<9}">
                        <li><a href="fore_type.action?typeId=${category.category_id}"<c:if
                                test="${i.index % 2 != 0}"> style="color: #FF0036"</c:if>>${fn:substring(category.category_name,0,fn:indexOf(category.category_name,' /'))}</a>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="home_nav">
        <div class="home_nav_title">
            <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/header_nav_title.png">
            <span>商品分类</span>
        </div>
        <a href="https://chaoshi.tmall.com/" target="_blank"><img
                src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/TB1ztBlaMMPMeJjy1XbXXcwxVXa-200-60.png"></a>
        <a href="https://www.tmall.hk/" target="_blank"><img
                src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/TB1t5ObaBxRMKJjy0FdXXaifFXa-200-60.png"></a>
        <a href="http://vip.tmall.com/" target="_blank">天猫会员</a>
        <a href="https://3c.tmall.com/" target="_blank">电器城</a>
        <a href="https://miao.tmall.com/" target="_blank">喵鲜生</a>
        <a href="http://yao.tmall.com/" target="_blank">医药馆</a>
        <a href="http://wt.tmall.com/" target="_blank">营业厅</a>
        <a href="https://meilihui.tmall.com/" target="_blank">魅力惠</a>
        <a href="https://www.alitrip.com/" target="_blank">飞猪旅行</a>
        <a href="https://suning.tmall.com/" target="_blank">苏宁易购</a>
    </div>
</nav>
<div class="banner">
    <c:forEach var="product" items="${requestScope.specialProductList}" varStatus="i">
        <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/${product.product_id}.jpg"
             name="${product.product_id}" id="banner${i.count}"
             <c:if test="${i.count == 1}">style="display: block;"</c:if> />
    </c:forEach>
</div>
<div class="banner_main">
    <ul class="banner_nav">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <li data-toggle="${category.category_id}" data-status="">
                <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/small/${category.category_id}.png">
                <a href="fore_type.action?typeId=${category.category_id}">${category.category_name}</a>
                <div class="banner_div" name="${category.category_name}">

                </div>
            </li>
        </c:forEach>
    </ul>
    <ul class="banner_slider">
        <li id="slider_1" style="background: rgba(255,255,255,0.4)"></li>
        <li id="slider_2"></li>
        <li id="slider_3"></li>
        <li id="slider_4"></li>
        <li id="slider_5"></li>
        <li id="slider_6"></li>
    </ul>
    <a href="#"></a>
</div>
<div class="banner_do">
    <div class="banner_goods">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <c:if test="${fn:length(category.productList)>0}">
                <div class="banner_goods_type">
                    <div class="banner_goods_title">
                        <span></span>
                        <p>${category.category_name}</p>
                    </div>
                    <a href="fore_type.action?typeId=${category.category_id}"><img class="banner_goods_show"
                                                                                   src="res/images/fore/WebsiteImage/show/${category.category_id}.jpg"></a>
                    <div class="banner_goods_items">
                        <c:forEach items="${category.productList}" var="product" varStatus="i">
                            <c:if test="${i.index<8}">
                                <div class="banner_goods_item">
                                    <a href="product/${product.product_id}" class="goods_link"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${product.singleProductImageList[0].productImage_src}">
                                    <a href="product/${product.product_id}"
                                       class="goods_name">${product.product_name}</a>
                                    <span class="goods_price">￥${product.product_sale_price}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="endDiv"></div>
</div>
<div class="banner_footer">
    <div class="tmall-ensure">
        <a href="http://pages.tmall.com/wow/seller/act/newpinzhibaozhang"></a>
        <a href="http://www.tmall.com/wow/seller/act/seven-day"></a>
        <a href="http://www.tmall.com/wow/seller/act/special-service?spm=875.7931836/B.a2226n1.3.71ca4265dmsU86"></a>
        <a href="http://service.tmall.com/support/tmall/tmallHelp.htm"></a>
    </div>
    <div class="tmall-desc">
        <dl>
            <dt><span>购物指南</span></dt>
            <dd><a href="http://register.tmall.com/" target="_blank">免费注册</a> <a
                    href="https://www.alipay.com/user/reg_select.htm" target="_blank">开通支付宝</a> <a
                    href="https://www.alipay.com/user/login.htm?goto=https%3A%2F%2Fwww.alipay.com%2Fuser%2Finpour_request.htm"
                    target="_blank">支付宝充值</a></dd>
        </dl>
        <dl>
            <dt><span>天猫保障</span></dt>
            <dd data-spm-anchor-id="875.7931836/B.a2226n1.i0.71ca4265hUvhyq"><a
                    href="http://service.tmall.com/support/tmall/knowledge-1140860.htm" target="_blank">发票保障</a> <a
                    href="http://service.tmall.com/support/tmall/knowledge-1124063.htm" target="_blank">售后规则</a> <a
                    href="http://service.tmall.com/support/tmall/knowledge-1126800.htm" target="_blank">缺货赔付</a></dd>
        </dl>
        <dl>
            <dt><span>支付方式</span></dt>
            <dd><a href="https://payservice.alipay.com/intro/index.htm?c=kjzf" target="_blank">快捷支付</a> <a
                    href="https://payservice.alipay.com/intro/index.htm?c=xyk" target="_blank">信用卡</a> <a
                    href="https://payservice.alipay.com/intro/index.htm?c=yeb" target="_blank">余额宝</a> <a
                    href="https://payservice.alipay.com/intro/index.htm?c=hb" target="_blank">蚂蚁花呗</a> <a
                    href="http://tms.alicdn.com/market/cainiao/codchn.php" target="_blank">货到付款</a></dd>
        </dl>
        <dl>
            <dt><span>商家服务</span></dt>
            <dd>
                <a href="http://guize.tmall.com/" target="_blank">天猫规则</a>
                <a href="http://zhaoshang.tmall.com/" target="_blank">商家入驻</a>
                <a href="http://shangjia.tmall.com/portal.htm" target="_blank">商家中心</a>
                <a href="http://peixun.tmall.com/" target="_blank">天猫智库</a>
                <a href="http://e56.tmall.com" target="_blank">物流服务</a>
                <a href="http://maowo.tmall.com/" target="_blank">喵言喵语</a>
                <a href="http://fw.tmall.com/" target="_blank">运营服务</a></dd>
        </dl>
        <dl>
            <dt>手机天猫</dt>
            <dd><a href="http://mobile.tmall.com/?spm=875.7931836/B.a2226n1.23.71ca4265hUvhyq" class="join"><img
                    src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/TB14VZKHXXXXXcAXXXX64VRZFXX-105-105.png"
                    width="105" height="105" alt="手机天猫"></a>
            </dd>
        </dl>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
</body>