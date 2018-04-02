$(function () {
    /******
     * event
     ******/
    //点击导航栏li时
    $(".menu_li").click(function () {
        var url = $(this).attr("data-toggle");
        //如果已选择li则退出
        if($(this).hasClass("menu_li_select")){
            return;
        } else {
            //清除已选中的li样式
            $(".menu_li_select").removeClass("menu_li_select");
            //设置当前li样式
            $(this).addClass("menu_li_select");
        }
        //ajax请求页面
        ajaxUtil.getPage(url,null,false);
        //设置文本
        var title = $(this).children("span").text();
        $("#div_home_title").children("span").text(title);
        document.title = "Tmall管理后台 - "+title;
    });
});
//tbody中tr的单击样式
function trDataStyle(obj) {
    var checkbox = obj.find(".cbx_select").first();
    if(checkbox.prop("checked")){
        checkbox.prop("checked",false);
    } else {
        checkbox.prop("checked",true);
    }
}
//
function getChildPage(url,data,title) {
    document.title = "Tmall管理后台 - "+title;
    //ajax请求页面
    ajaxUtil.getPage(url,data,true);
}