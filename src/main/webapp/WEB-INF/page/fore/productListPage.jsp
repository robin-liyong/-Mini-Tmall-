<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/fore/fore_productList.js"></script>
<link href="${pageContext.request.contextPath}/res/css/fore/fore_nav.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/res/css/fore/fore_foot.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/res/css/fore/fore_productList.css" rel="stylesheet">
<body>
<title>${requestScope.searchValue}-天猫Tmall.com-理想生活上天猫</title>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}"><img
                    src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/tmallLogoA.png"></a>
        </div>
        <div class="shopSearchHeader">
            <form action="fore_search.action" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="goodsName" placeholder="搜索 天猫 商品/品牌/店铺"
                           value="${requestScope.searchValue}">
                    <input type="submit" value="搜 索" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li><a href="fore_type.action?category_id=${category.id}">${category.name}</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="context">
    <c:choose>
        <c:when test="${requestScope.productList != null}">
            <div class="context_menu">
                <ul>
                    <li><a href="fore_search.action?goodsName=${requestScope.searchValue}">综合</a></li>
                    <li><a href="fore_search.action?goodsName=${requestScope.searchValue}&sortType=new">新品</a></li>
                    <li><a href="fore_search.action?goodsName=${requestScope.searchValue}&sortType=sale">销量</a></li>
                    <li><a href="fore_search.action?goodsName=${requestScope.searchValue}&sortType=price">价格</a></li>
                </ul>
            </div>
            <div class="context_main">
                <c:forEach items="${requestScope.productList}" var="product">
                    <div class="context_productStyle">
                        <div class="context_product">
                            <a href="product/${product.product_id}" target="_blank"><img class="context_product_imgMain"
                                                                                         src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${requestScope.product.singleProductImageList[0].productImage_src}"/></a>
                            <ul class="context_product_imgList">
                                <c:forEach items="${product.singleProductImageList}" var="img">
                                    <li><img
                                            src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${img.productImage_src}"/>
                                    </li>
                                </c:forEach>
                            </ul>
                            <p class="context_product_price"><span>¥</span>${product.product_sale_price}</p>
                            <p class="context_product_name"><a href="product/${product.product_id}"
                                                               target="_blank">${product.product_name}</a></p>
                            <p class="context_product_shop"><span>贤趣${product.product_category.category_name}旗舰店</span>
                            </p>
                            <p class="context_product_status">
                                <span class="status_left">总成交<em>${product.product_sale_count}笔</em></span>
                                <span class="status_middle">评价<em>${product.product_review_count}</em></span>
                                <span class="status_right"><img
                                        src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/T11lggFoXcXXc1v.nr-93-93.png"/></span>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="error">
                <h2>喵~没找到与“${requestScope.searchValue}”相关的 商品 哦，要不您换个关键词我帮您再找找看</h2>
                <h3>建议您：</h3>
                <ol>
                    <li>看看输入的文字是否有误</li>
                    <li>调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”</li>
                    <li>
                        <form action="fore_search.action" method="get">
                            <input title="查询产品" type="text" class="errorInput" name="goodsName"
                                   value="${requestScope.searchValue}">
                            <input type="submit" value="去淘宝搜索" class="errorBtn">
                        </form>
                    </li>
                </ol>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="include/footer.jsp" %>
