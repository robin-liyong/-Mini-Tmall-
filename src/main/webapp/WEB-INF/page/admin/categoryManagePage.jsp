<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script>
        $(function () {
            //检索数据集
            var dataList = {
                "category_name": null
            };
            /******
             * event
             * *****/
            //点击查询按钮时
            $("#btn_category_submit").click(function () {
                var category_name = $.trim($("#input_category_name").val());
                //封装数据
                dataList.category_name = encodeURI(category_name);

                getData($(this),"admin/category/1/10",dataList);
            });
            //点击刷新按钮时
            $("#btn_category_refresh").click(function () {
                //获取数据
                getData($(this), "admin/category/1/10",null);
            });
            //点击table中的数据时
            $("#table_category_list").find(">tbody>tr").click(function () {
                trDataStyle($(this));
            });
        });
        //获取分类数据
        function getData(object, url, dataObject) {
            var table = $("#table_category_list");
            var tbody = table.children("tbody").first();
            $.ajax({
                url: url,
                type: "get",
                data: dataObject,
                success: function (data) {
                    //清空原有数据
                    tbody.empty();
                    //设置样式
                    $(".loader").css("display","none");
                    object.attr("disabled",false);
                    if(data.categoryList.length > 0) {
                        //显示分类统计数据
                        $("#category_count_data").text(data.categoryCount);
                        for (var i in data.categoryList) {
                            var category_id = data.categoryList[i].category_id;
                            var category_name = data.categoryList[i].category_name;
                            //显示分类数据
                            tbody.append("<tr><td><input type='checkbox' class='cbx_select' id='cbx_category_select_" + category_id + "'><label for='cbx_category_select_" + category_id + "'></label></td><td>" + category_name + "</td><td><span class='td_special'><a href='#'>详情</a></span></td><td hidden>" + category_id + "</td></tr>");
                        }
                        //绑定事件
                        tbody.children("tr").click(function () {
                            trDataStyle($(this));
                        });
                    }
                },
                beforeSend: function () {
                    $(".loader").css("display", "block");
                    object.attr("disabled",true);
                },
                error: function () {

                }
            });
        }
    </script>
</head>
<body>
<div class="frm_div text_info">
    <form id="form_category" class="form_main">
        <div class="frm_group">
            <label class="frm_label" id="lbl_category_name" for="input_category_name">分类名称</label>
            <input class="frm_input" id="input_category_name" type="text" maxlength="50"/>
            <input class="frm_btn" id="btn_category_submit" type="button" value="查询"/>
            <input class="frm_btn frm_clear" id="btn_clear" type="button" value="重置"/>
        </div>
        <div class="frm_group_last">
            <input class="frm_btn frm_add" id="btn_category_add" type="button" value="添加一个分类"/>
            <input class="frm_btn frm_refresh" id="btn_category_refresh" type="button" value="刷新分类列表"/>
        </div>
    </form>
</div>
<div class="data_count_div text_info">
    <svg class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2522" width="16"
         height="16">
        <path d="M401.976676 735.74897c-88.721671 0-172.124196-34.635845-234.843656-97.526197-62.724577-62.86784-97.271394-146.453537-97.271394-235.358379s34.546817-172.490539 97.276511-235.361449c62.715367-62.887282 146.117892-97.522104 234.838539-97.522104 88.719624 0 172.135452 34.633798 234.881518 97.522104 62.704111 62.875003 97.235578 146.4607 97.235578 235.361449 0 88.901773-34.530444 172.487469-97.231485 235.358379C574.112128 701.116195 490.6963 735.74897 401.976676 735.74897zM401.976676 121.204479c-75.012438 0-145.533584 29.290093-198.572568 82.474386-109.585861 109.834524-109.585861 288.539602-0.004093 398.36901 53.043077 53.188386 123.564223 82.47848 198.577684 82.47848 75.015507 0 145.553027-29.291117 198.620663-82.47848C710.126918 492.220514 710.126918 313.511343 600.593246 203.678866 547.530726 150.496619 476.992183 121.204479 401.976676 121.204479z"
              p-id="2523" fill="#FF7874">
        </path>
        <path d="M932.538427 958.228017c-6.565533 0-13.129019-2.508123-18.132986-7.52437L606.670661 642.206504c-9.989515-10.014074-9.969049-26.231431 0.045025-36.220946s26.230408-9.969049 36.220946 0.045025l307.73478 308.497143c9.989515 10.014074 9.969049 26.231431-0.045025 36.220946C945.627537 955.735244 939.081447 958.228017 932.538427 958.228017z"
              p-id="2524" fill="#FF7874">
        </path>
    </svg>
    <span class="data_count_title">查看合计</span>
    <span>分类总数:</span>
    <span class="data_count_value" id="category_count_data">${requestScope.categoryCount}</span>
    <span class="data_count_unit">个</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_category_list">
        <thead class="text_info">
        <tr>
            <th><input type="checkbox" class="cbx_select" id="cbx_select_all"><label for="cbx_select_all"></label></th>
            <th><span>分类名称</span></th>
            <th>操作</th>
            <th hidden>分类ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.categoryList}" var="category">
            <tr>
                <td><input type="checkbox" class="cbx_select" id="cbx_category_select_${category.category_id}"><label for="cbx_category_select_${category.category_id}"></label></td>
                <td>${category.category_name}</td>
                <td><span class="td_special"><a href="#">详情</a></span></td>
                <td hidden><span class="category_id">${category.category_id}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="loader"></div>
</div>
</body>
</html>
