<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
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
                        <li><a href="fore_type.action?typeId=${category.category_id}" <c:if
                                test="${i.index%2!=0}"> style="color: #FF0036"</c:if>>${fn:substring(category.category_name,0,fn:indexOf(category.category_name,' /'))}</a>
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
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/1.jpg" id="banner1"
         style="display: block">
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/2.jpg" id="banner2">
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/3.jpg" id="banner3">
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/4.jpg" id="banner4">
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/5.jpg" id="banner5">
    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/banner/6.jpg" id="banner6">
</div>
<div class="banner_main">
    <ul class="banner_nav">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <li>
                <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/small/${category.category_id}.png">
                <a href="fore_type.action?typeId=${category.category_id}">${category.category_name}</a>
                <div class="banner_div">
                    <c:forEach items="${category.productList}" var="productList">
                        <div class="hot_word">
                            <c:forEach items="${productList}" var="product">
                                <c:if test="${product.product_title != ''}">
                                    <a href="fore_goods.action?gid=${product.product_id}">${fn:substring(product.product_title,0,fn:indexOf(product.product_title,' '))}</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
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
</div>
<div class="banner_do">
    <div class="banner_goods">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <c:if test="${category.productList.size()>0}">
                <div class="banner_goods_type">
                    <div class="banner_goods_title">
                        <span></span>
                        <p>${category.category_name}</p>
                    </div>
                    <a href="fore_type.action?typeId=${category.id}"><img class="banner_goods_show"
                                                                          src="res/WebsiteImage/show/${category.id}.jpg"></a>
                    <div class="banner_goods_items">
                        <c:forEach items="${category.productList.get(0)}" var="productList" varStatus="i">
                            <c:if test="${i.index<8}">
                                <div class="banner_goods_item">
                                    <a href="fore_goods.action?gid=${productList.product_id}" class="goods_link"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/fore/item/productSinglePicture/${productList.singleProductImageList[0].id}.jpg">
                                    <a href="fore_goods.action?gid=${productList.product_id}"
                                       class="goods_name">${productList.product_name}</a>
                                    <span class="goods_price">￥${fn:substring(productList.product_sale_price,0,fn:indexOf(productList.product_sale_price,'.'))}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
<%@ include file="include/footer.jsp" %>
</body>