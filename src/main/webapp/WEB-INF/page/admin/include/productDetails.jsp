<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
    $(function () {
        if($("#span_product_id").text() === ""){
            //刷新下拉框
            $('#select_product_category').selectpicker('refresh');
        } else {
            //设置产品种类值
            $('#select_product_category').selectpicker('val',String(${requestScope.product.product_category.category_id}));
            //设置产品状态
            var product_isEnabled = String(${requestScope.product.product_isEnabled});
            $("input[name='radio_product_isEnabled']").each(function () {
                if($(this).val() === product_isEnabled){
                    $(this).prop("checked",true);
                    return false;
                }
            });
        }
    });
    //图片预览
    function imgPreview(fileDom) {
        //判断是否支持FileReader
        if(window.FileReader){
            var reader = new FileReader();
        } else {
            alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
            return;
        }
        //获取文件
        var file = fileDom.files[0];
        var imageType = /^image\//;
        //判断文件格式
        if(!imageType.test(file.type)){
            alert("请选择图片！");
            return;
        }
        //读取
        reader.onload = function (e) {
            $(fileDom).parents("li").prev("li").find("img").attr("src",e.target.result);
        };
        reader.readAsDataURL(file);
    }
    </script>
    <style rel="stylesheet">
        .bootstrap-select:not([class*=col-]):not([class*=form-control]):not(.input-group-btn){
            margin: 0 150px 0 0;
        }
        .frm_input{
            margin-right: 150px;
        }
    </style>
</head>
<body>
<div class="details_div_first">
    <label class="frm_label" id="lbl_product_category_id" for="select_product_category">产品类型</label>
    <select class="selectpicker" id="select_product_category" data-size="8">
        <c:forEach items="${requestScope.categoryList}" var="category">
            <option value="${category.category_id}">${category.category_name}</option>
        </c:forEach>
    </select>
    <label class="frm_label" id="lbl_product_isEnabled" for="radio_product_isEnabled_true">产品状态</label>
    <input id="radio_product_isEnabled_true" name="radio_product_isEnabled" type="radio" value="0" checked>
    <label class="frm_label" id="lbl_product_isEnabled_true" for="radio_product_isEnabled_true">销售中</label>
    <input id="radio_product_isEnabled_false" name="radio_product_isEnabled" type="radio" value="1">
    <label class="frm_label" id="lbl_product_isEnabled_false" for="radio_product_isEnabled_false">停售中</label>
    <input id="radio_product_isEnabled_special" name="radio_product_isEnabled" type="radio" value="2">
    <label class="frm_label" id="lbl_product_isEnabled_special" for="radio_product_isEnabled_special">促销中</label>
</div>
<div class="details_div">
    <span class="details_title">基本信息</span>
    <div class="frm_div">
        <label class="frm_label" id="lbl_product_id">产品编号</label>
        <span class="details_value" id="span_product_id">${requestScope.product.product_id}</span>
        <label class="frm_label" id="lbl_product_create_date">上架日期</label>
        <span class="details_value" id="span_product_create_date">${requestScope.product.product_create_date}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label" id="lbl_product_name" for="input_product_name">产品名称</label>
        <input class="frm_input" id="input_product_name" type="text" maxlength="50" value="${requestScope.product.product_name}"/>
        <label class="frm_label" id="lbl_product_title" for="input_product_title">产品标题</label>
        <input class="frm_input" id="input_product_title" type="text" maxlength="50" value="${requestScope.product.product_title}"/>
    </div>
    <div class="frm_div_last">
        <label class="frm_label" id="lbl_product_sale_price" for="input_product_price">产品原价</label>
        <input class="frm_input details_unit"  id="input_product_price" type="text" maxlength="10" value="${requestScope.product.product_price}"/>
        <span class="details_unit">元</span>
        <label class="frm_label" id="lbl_product_sale_place" for="input_product_sale_price">产品促销价</label>
        <input class="frm_input details_unit"  id="input_product_sale_price" type="text" maxlength="10" value="${requestScope.product.product_sale_price}"/>
        <span class="details_unit">元</span>
    </div>
</div>
<div class="details_div">
    <span class="details_title">属性值信息</span>
    <c:forEach items="${requestScope.propertyList}" var="property" varStatus="status">
        <c:if test="${status.index % 2 == 0}">
            <div class="frm_div">
        </c:if>
        <label class="frm_label lbl_property_name" id="lbl_product_property_${property.property_id}" for="input_product_property_${property.property_id}">${property.property_name}</label>
        <c:choose>
            <c:when test="${property.propertyValueList != null}">
                <c:forEach items="${property.propertyValueList}" var="propertyValue">
                    <input class="frm_input" id="input_product_property_${property.property_id}" type="text" maxlength="50" value="${propertyValue.propertyValue_value}" data-pvid="${propertyValue.propertyValue_id}"/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <input class="frm_input" id="input_product_property_${property.property_id}" type="text" maxlength="50"/>
            </c:otherwise>
        </c:choose>
        <c:if test="${status.index % 2 != 0}">
            </div>
        </c:if>
    </c:forEach>
</div>
<div class="details_div">
    <span class="details_title">展示图片</span>
    <ul class="details_picList">
        <c:forEach items="${requestScope.product.singleProductImageList}" var="image">
            <li><img src="${image.productImage_src}" id="pic_single_${image.productImage_id}" width="128px" height="128px"/></li>
        </c:forEach>
        <li><div class="fileUpload"><input type="file" onchange="imgPreview(this)"></div></li>
    </ul>
</div>
</body>
</html>
