<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script>
        //检索数据集
        var dataList = {
            "reward_name": null,
            "reward_lowest_amount": null,
            "reward_highest_amount": null,
            "reward_isEnabled_array": null,
            "orderBy": null,
            "isDesc": true
        };
        $(function () {
            /******
             * event
             ******/
            //点击查询按钮时
            $("#btn_reward_submit").click(function () {
                var reward_name = $.trim($("#input_reward_name").val());
                var lowest_price = $.trim($("#input_reward_low_amount").val());
                var highest_price = $.trim($("#input_reward_high_amount").val());
                //打赏状态数组
                var status_array = [];
                $("input[name = checkbox_reward_isEnabled]:checked").each(function () {
                    status_array.push($(this).val());
                });
                //校验数据合法性
                if( isNaN(lowest_price) || isNaN(highest_price) ){
                    styleUtil.errorShow($('#text_reward_msg'),"金额输入格式有误！");
                    return;
                }
                //封装数据
                dataList.reward_name = encodeURI(reward_name);
                dataList.reward_lowest_amount = lowest_price;
                dataList.reward_highest_amount = highest_price;
                dataList.reward_isEnabled_array = status_array;

                getData($(this), "admin/reward/0/10", dataList);
            });
            //点击刷新按钮时
            $("#btn_reward_refresh").click(function () {
                //清除数据
                dataList.reward_name = null;
                dataList.reward_lowest_amount = null;
                dataList.reward_highest_amount = null;
                dataList.reward_isEnabled_array = null;
                dataList.orderBy = null;
                dataList.isDesc = true;
                //获取数据
                getData($(this), "admin/reward/0/10", null);
                //清除排序样式
                var table = $("#table_reward_list");
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                table.find("th.data_info").attr("data-sort","asc");
            });
            //点击th排序时
            $("th.data_info").click(function () {
                var table = $("#table_reward_list");
                if(table.find(">tbody>tr").length <= 1){
                    return;
                }
                //获取排序字段
                dataList.orderBy = $(this).attr("data-name");
                //是否倒序排序
                dataList.isDesc = $(this).attr("data-sort")==="asc";

                getData($(this), "admin/reward/0/10", dataList);
                //设置排序
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                if(dataList.isDesc){
                    $(this).attr("data-sort","desc").children(".orderByAsc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByDesc").addClass("orderBySelect").css("opacity","1");
                } else {
                    $(this).attr("data-sort","asc").children(".orderByDesc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByAsc").addClass("orderBySelect").css("opacity","1");
                }
            });
            //点击table中的数据时
            $("#table_reward_list").find(">tbody>tr").click(function () {
                trDataStyle($(this));
            });
        });
        //获取打赏数据
        function getData(object, url, dataObject) {
            var table = $("#table_reward_list");
            var tbody = table.children("tbody").first();
            $.ajax({
                url: url,
                type: "get",
                data: dataObject,
                traditional: true,
                success: function (data) {
                    //清空原有数据
                    tbody.empty();
                    //设置样式
                    $(".loader").css("display","none");
                    object.attr("disabled",false);
                    //显示打赏统计数据
                    $("#reward_count_data").text(data.rewardCount);
                    if(data.rewardList.length > 0) {
                        for (var i = 0; i < data.rewardList.length; i++) {
                            var isEnabledClass;
                            var isEnabledTitle;
                            var isEnabled;
                            switch (data.rewardList[i].reward_state) {
                                case 0:
                                    isEnabledClass = "td_warn";
                                    isEnabledTitle = "打赏待审核";
                                    isEnabled = "待审核";
                                    break;
                                case 1:
                                    isEnabledClass = "td_success";
                                    isEnabledTitle = "打赏已审核";
                                    isEnabled = "已审核";
                                    break;
                                default:
                                    isEnabledClass = "td_error";
                                    isEnabledTitle = "打赏未确认";
                                    isEnabled = "未确认";
                                    break;
                            }
                            var reward_id = data.rewardList[i].reward_id;
                            var reward_name = data.rewardList[i].reward_name;
                            var reward_amount = data.rewardList[i].reward_amount;
                            var reward_createDate = data.rewardList[i].reward_createDate;
                            var reward_content = data.rewardList[i].reward_content;
                            //显示打赏数据
                            tbody.append("<tr>" +
                                "<td>" +
                                "<input type='checkbox' class='cbx_select' id='cbx_reward_select_" + reward_id + "'>" +
                                "<label for='cbx_reward_select_" + reward_id + "'></label>" +
                                "</td>" +
                                "<td title='" + reward_name + "'>" + reward_name + "</td>" +
                                "<td title='" + reward_amount + "'>" + reward_amount + "</td>" +
                                "<td title='" + reward_createDate + "'>" + reward_createDate + "</td>" +
                                "<td><span class='" + isEnabledClass + "' title='"+isEnabledTitle+"'>"+ isEnabled + "</span></td>" +
                                "<td title='" + reward_content + "'>" + reward_content + "</td>" +
                                "<td><span class='td_special' title='查看打赏详情'><a href='javascript:void(0)' onclick='getChildPage(this)'>详情</a></span></td>" +
                                "<td hidden class='reward_id'>" + reward_id + "</td></tr>");
                        }
                        //绑定事件
                        tbody.children("tr").click(function () {
                            trDataStyle($(this));
                        });
                        //分页
                        var pageUtil = {
                            index: data.pageUtil.index,
                            count: data.pageUtil.count,
                            total: data.pageUtil.total,
                            totalPage: data.totalPage
                        };
                        createPageDiv($(".loader"), pageUtil);
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

        //获取打赏子界面
        function getChildPage(obj) {
            alert("待完成")
            // //设置样式
            // $("#div_home_title").children("span").text("打赏详情");
            // document.title = "Tmall管理后台 - 打赏详情";
            // //ajax请求页面
            // ajaxUtil.getPage("reward/" + $(obj).parents("tr").find(".reward_id").text(), null, true);
        }

        //获取页码数据
        function getPage(index) {
            getData($(this), "admin/reward/" + index + "/10", dataList);
        }
    </script>
    <style rel="stylesheet">
        #text_cut{
            position: relative;
            right: 10px;
            color: #ccc;
        }
        #lbl_reward_isEnabled_special{
            margin-right: 20px;
        }
    </style>
</head>
<body>
<div class="frm_div text_info">
    <div class="frm_group">
        <label class="frm_label" id="lbl_reward_name" for="input_reward_name">打赏人昵称</label>
        <input class="frm_input" id="input_reward_name" type="text" maxlength="50"/>
        <input class="frm_btn" id="btn_reward_submit" type="button" value="查询"/>
        <input class="frm_btn frm_clear" id="btn_clear" type="button" value="重置"/>
    </div>
    <div class="frm_group">
        <label class="frm_label" id="lbl_reward_isEnabled" for="checkbox_reward_isEnabled_false">打赏状态</label>
        <input id="checkbox_reward_isEnabled_false" name="checkbox_reward_isEnabled" type="checkbox" value="0" checked>
        <label class="frm_label" id="lbl_reward_isEnabled_true" for="checkbox_reward_isEnabled_false">待审核</label>
        <input id="checkbox_reward_isEnabled_true" name="checkbox_reward_isEnabled" type="checkbox" value="1" checked>
        <label class="frm_label" id="lbl_reward_isEnabled_false" for="checkbox_reward_isEnabled_true">已审核</label>
        <input id="checkbox_reward_isEnabled_special" name="checkbox_reward_isEnabled" type="checkbox" value="2" checked>
        <label class="frm_label" id="lbl_reward_isEnabled_special" for="checkbox_reward_isEnabled_special">未确认</label>

        <label class="frm_label"  id="lbl_reward_amount" for="input_reward_low_amount">金额</label>
        <input class="frm_input frm_num"  id="input_reward_low_amount" type="text" placeholder="最低金额" maxlength="10">
        <span id="text_cut">—</span>
        <input class="frm_input frm_num"  id="input_reward_high_amount" type="text" placeholder="最高金额" maxlength="10">
        <span class="frm_error_msg" id="text_reward_msg"></span>
    </div>
    <div class="frm_group_last">
        <input class="frm_btn frm_refresh" id="btn_reward_refresh" type="button" value="刷新打赏列表"/>
    </div>
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
    <span>打赏总数:</span>
    <span class="data_count_value" id="reward_count_data">${requestScope.rewardCount}</span>
    <span class="data_count_unit">条</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_reward_list">
        <thead class="text_info">
        <tr>
            <th><input type="checkbox" class="cbx_select" id="cbx_select_all"><label for="cbx_select_all"></label></th>
            <th>打赏人昵称</th>
            <th class="data_info" data-sort="asc" data-name="reward_amount">
                <span>金额</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="reward_createDate">
                <span>打赏时间</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            <th class="data_info" data-sort="asc" data-name="reward_state">
                <span>状态</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th>留言</th>
            <th>操作</th>
            <th hidden class="reward_id">打赏ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.rewardList}" var="reward">
            <tr>
                <td><input type="checkbox" class="cbx_select" id="cbx_reward_select_${reward.reward_id}"><label for="cbx_reward_select_${reward.reward_id}"></label></td>
                <td title="${reward.reward_name}">${reward.reward_name}</td>
                <td title="${reward.reward_amount}">${reward.reward_amount}</td>
                <td title="${reward.reward_createDate}">${reward.reward_createDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${reward.reward_state==0}"><span class="td_warn" title="打赏待审核">待审核</span></c:when>
                        <c:when test="${reward.reward_state==1}"><span class="td_success" title="打赏已审核">已审核</span></c:when>
                        <c:otherwise><span class="td_error" title="打赏未确认">未确认</span></c:otherwise>
                    </c:choose>
                </td>
                <td title="${reward.reward_content}">${reward.reward_content}</td>
                <td><span class="td_special" title="查看打赏详情"><a href="javascript:void(0)"
                                                               onclick="getChildPage(this)">详情</a></span></td>
                <td hidden><span class="reward_id">${reward.reward_id}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="include/page.jsp" %>
    <div class="loader"></div>
</div>
</body>
</html>
