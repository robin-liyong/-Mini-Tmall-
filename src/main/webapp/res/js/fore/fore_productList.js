$(function () {
    $(".context_product_imgList>li").click(function () {
        if ($(this).hasClass('context_product_Li_click')) {
            return;
        }
        var ul = $(this).parent();
        ul.children().removeClass('context_product_Li_click');
        $(this).addClass('context_product_Li_click');
        ul.prev('a').children('img').attr("src", $(this).children('img').attr("src"));
    });
    var url = window.location.href;
    var index = url.lastIndexOf('=');
    var sortType = url.substring(index + 1);
    switch (sortType) {
        case 'new':
            $(".context_menu").find('li').eq(1).addClass('now');
            break;
        case 'sale':
            $(".context_menu").find('li').eq(2).addClass('now');
            break;
        case 'price':
            $(".context_menu").find('li').eq(3).addClass('now');
            break;
        case 'normal':
        default:
            $(".context_menu").find('li').eq(0).addClass('now');
            break;
    }
    //搜索框验证
    $('form').submit(function () {
        if ($(this).find("input[name='goodsName']").val() === "") {
            alert("请输入关键字！");
            return false;
        }
    });
});