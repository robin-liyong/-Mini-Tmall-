<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<head>
    <script src="${pageContext.request.contextPath}/res/js/fore/fore_productDetails.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_nav.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_foot.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/res/css/fore/fore_productDetails.css" rel="stylesheet">
    <title>${requestScope.product.product_name}-tmall.com天猫</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <a href="${pageContext.request.contextPath}"><img
                src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/tmallLogoB.png"></a>
        <span class="shopNameHeader">贤趣${requestScope.product.product_category.category_name}官方旗舰店</span>
        <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/detailsHeaderA.png"
             class="shopAssessHeader">
        <div class="shopSearchHeader">
            <form action="fore_search.action" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="product_name" placeholder="搜索 天猫 商品/品牌/店铺">
                    <input type="submit" value="搜天猫" class="searchTmall">
                </div>
                <input type="submit" value="搜本店" class="searchShop">
            </form>
        </div>
    </div>
</nav>
<div class="shopImg">
    <img src="${pageContext.request.contextPath}/res/images/item/categoryPicture/${requestScope.product.product_category.category_image_src}">
</div>
<div class="context">
    <div class="context_left">
        <div class="context_img_ks">
            <img src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${requestScope.product.singleProductImageList[0].productImage_src}"
                 width="800px" height="800px">
        </div>
        <div class="context_img">
            <img src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${requestScope.product.singleProductImageList[0].productImage_src}"
                 class="context_img_main" width="400px" height="400px"/>
            <div class="context_img_winSelector"></div>
        </div>
        <ul class="context_img_ul">
            <c:forEach var="img" items="${requestScope.product.singleProductImageList}">
                <li class="context_img_li"><img
                        src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${img.productImage_src}"/>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="context_info">
        <div class="context_info_name_div">
            <p class="context_info_name">${requestScope.product.product_name}</p>
            <span class="context_info_title">${requestScope.product.product_title}</span>
        </div>
        <div class="context_info_main">
            <div class="context_info_main_ad">
                <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/context_ad.png">
                <span>全天猫实物商品通用</span>
                <a href="#">去刮券<img
                        src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/tmallItemContentB.png"></a>
            </div>
            <dl class="context_price_panel">
                <dt>价格</dt>
                <dd><em>¥</em><span>${requestScope.product.product_price}0</span></dd>
            </dl>
            <dl class="context_promotePrice_panel">
                <dt>促销价</dt>
                <dd><em>¥</em><span>${requestScope.product.product_sale_price}0</span></dd>
            </dl>
        </div>
        <ul class="context_other_panel">
            <li>总销量<span><c:choose><c:when
                    test="${requestScope.product.product_sale_count != null}">${requestScope.product.product_sale_count}</c:when><c:otherwise>0</c:otherwise></c:choose></span>
            </li>
            <li>累计评价<span>${requestScope.product.product_review_count}</span></li>
            <li class="tmall_points">送天猫积分<span><fmt:formatNumber type="number"
                                                                  value="${requestScope.product.product_sale_price/10}"
                                                                  maxFractionDigits="0"/></span></li>
        </ul>
        <dl class="context_info_member">
            <dt>数量</dt>
            <dd>
                <input type="text" value="1" maxlength="8" title="请输入购买量" class="context_buymember">
                <input type="hidden" id="stock" value="1000">
                <span class="amount-btn">
                    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/up.png"
                         class="amount_value_up">
                    <img src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/down.png"
                         class="amount_value-down">
                </span>
                <span class="amount_unit">件</span>
                <em>库存1000件</em>
            </dd>
        </dl>
        <div class="context_buy">
            <form action="#" method="post">
                <input class="context_buyNow" type="submit" value="立即购买"/>
                <input class="context_addBuyCar" type="submit" value="加入购物车"/>
                <input type="hidden" name="gid" value="${requestScope.product.product_id}">
                <input type="hidden" id="tid" value="${requestScope.product.product_category.category_id}">
            </form>
        </div>
        <div class="context_clear">
            <span>服务承诺</span>
            <a href="#">正品保证</a>
            <a href="#">极速退款</a>
            <a href="#">七天无理由退换</a>
        </div>
    </div>
    <div class="context_ul">
        <div class="context_ul_head">
            <s></s>
            <span>看了又看</span>
        </div>
        <div class="context_ul_goodsList">
            <ul>
                <c:forEach items="${requestScope.loveProductList}" var="product">
                    <li class="context_ul_main">
                        <div class="context_ul_img">
                            <a href="/tmall/product/${product.product_id}">
                                <img src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${product.singleProductImageList[0].productImage_src}">
                            </a>
                            <p>¥${product.product_sale_price}0</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <input type="hidden" id="guessNumber" value="${requestScope.guessNumber}">
        </div>
        <ul class="context_ul_trigger">
            <li class="ul_trigger_up"><a href="#"></a></li>
            <li class="ul_trigger_down"><a href="#"></a></li>
        </ul>
    </div>
</div>
<div class="mainwrap">
    <div class="J_TabBarBox">
        <ul>
            <li class="J_GoodsDetails">
                <a href="javascript:void(0)" class="detailsClick" onclick="getDetailsPage(this,'J_details')">商品详情</a>
            </li>
            <li class="J_GoodsReviews">
                <a href="javascript:void(0)"
                   onclick="getDetailsPage(this,'J_review')">累计评价<span>${requestScope.product.product_review_count}</span></a>
            </li>
        </ul>
    </div>
    <div class="J_choose">
        <%@include file="include/J_details.jsp" %>
        <%@include file="include/J_review.jsp" %>
    </div>
    <div class="J_img">
        <c:forEach items="${requestScope.product.detailProductImageList}" var="image">
            <img src="${pageContext.request.contextPath}/res/images/item/productDetailsPicture/${image.productImage_src}"/>
        </c:forEach>
    </div>
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