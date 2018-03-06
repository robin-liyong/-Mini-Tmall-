$(function () {
    //默认ajax页面为首页
    var pageURL = $(".menu_li_select").eq(0).attr("data-toggle");
    getPage(pageURL,null,false);
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
        //设置标题栏文本
        $("#div_home_title").find(">span").text($(this).children("span").text());
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
            url: "admin/"+url,
            type:"get",
            data: data,
            contentType: "text/html;charset=UTF-8",
            success : function (data) {
                $("#div_home_context_main").html(data);
            },
            beforeSend: function () {

            },
            error: function () {

            }
        });
    }
}