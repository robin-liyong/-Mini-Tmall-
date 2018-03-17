$(function () {
    //默认页面为首页
    var pageURL = $(".menu_li_select").first().attr("data-toggle");

    /******
     * event
     ******/
    //点击导航栏li时
    $(".menu_li").click(function () {
        var url = $(this).attr("data-toggle");
        //如果已选择li则退出
        if(pageURL===url){
            return;
        } else {
            pageURL = url;
        }
        //清除已选中的li样式
        $(".menu_li_select").removeClass("menu_li_select");
        //设置当前li样式
        $(this).addClass("menu_li_select");
        //设置文本
        var title = $(this).children("span").text();
        $("#div_home_title").find(">span").text(title);
        document.title = "Tmall管理后台 - "+title;
        //ajax请求页面
        getPage(pageURL,null,false);
    });
});
//页面切换
function getPage(url,data,isChild) {
    console.debug(url);
    if(isChild){
        //清除已选中的li样式
        $(".menu_li_select").removeClass("menu_li_select");
    }
    if(url !== null && url !== ""){
        $.ajax({
            url: "admin"+url,
            type:"get",
            data: data,
            contentType: "text/html;charset=UTF-8",
            success : function (data) {
                $("#div_home_context_main").html(data);
                if(!isChild){
                    /******
                     * event
                     ******/
                    //获得表单元素焦点时
                    $(".frm_input,.frm_radio").focus(function () {
                        styleUtil.errorHide($("#text_product_msg"));
                    });
                    //点击table中的全选框时
                    $("#cbx_select_all").click(function () {
                        if($(this).prop("checked")){
                            $("td>.cbx_select").prop("checked",true);
                        } else {
                            $("td>.cbx_select").prop("checked",false);
                        }
                        styleUtil.errorHide($("#text_tools_msg"));
                    });
                    //点击table中的选框时
                    $("td>.cbx_select").click(function () {
                        styleUtil.errorHide($("#text_tools_msg"));
                    });
                    //点击table中的数据时
                    $("tbody>tr").click(function () {
                        var checkbox = $(this).find(".cbx_select").first();
                        if(checkbox.prop("checked")){
                            checkbox.prop("checked",false);
                        } else {
                            checkbox.prop("checked",true);
                        }
                    });
                    //点击重置按钮时
                    $('#btn_clear').click(function () {
                        $(".form_main")[0].reset();
                        $(".selectpicker").selectpicker("refresh");
                    });
                }
            },
            beforeSend: function () {
                $(".loader").css("display","block");
            },
            error: function () {

            }
        });
    }
}