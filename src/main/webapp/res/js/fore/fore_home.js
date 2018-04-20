$(function () {
    var index = 1;
    //主页切换动画
    var timer;
    getTimer();
    window.onfocus = function () {
        getTimer();
    };
    window.onblur = function () {
        clearInterval(timer);
    };

    function getTimer() {
        timer = setInterval(function () {
            var banner = $(".banner");
            var img = $("#banner" + index);
            var sliders = $(".banner_slider>li");
            var color;
            switch (index) {
                case 1:
                    color = "#0F1322";
                    break;
                case 2:
                case 4:
                    color = "#E8E8E8";
                    break;
                case 3:
                    color = "#FBB4B0";
                    break;
                case 5:
                    color = "#F4E0D5";
                    break;
                default:
                    color = "#BD160D";
            }
            if (banner.css("opacity") != "1") {
                $("#slider_" + index).css("background", "rgba(255,255,255,0.4)");
                img.css("display", "block").stop(true, true).animate({opacity: 1}, 200);
                banner.css("background-color", color).stop(true, true).animate({opacity: "1"}, 200);
            } else {
                setTimeout(function () {
                    sliders.css("background", "rgba(0,0,0,0.4)");
                    banner.stop(true, true).animate({opacity: "0.7"}, 200);
                    img.stop(true, true).animate({opacity: "0"}, 200);
                    index++;
                    if (index > 6) {
                        index = 1;
                    }
                }, 1700);
            }
        }, 2000);
    }

    //热词样式
    $(".hot_word").find(">a").each(function () {
        var random = parseInt(Math.random() * (7));
        switch (random) {
            case 1:
            case 3:
            case 5:
                $(this).css("color", "#6347ED");
                break;
        }
    });
    //导航动画
    $(".banner_nav>li").hover(function () {
        $(this).find(">a").css("color", "#6347ED");
        $(this).find(">.banner_div").css("display", "block");
    }, function () {
        $(this).find(">a").css("color", "#FFFFFF");
        $(this).find(".banner_div").css("display", "none");
    });
    //搜索框验证
    $('form').submit(function () {
        if ($(this).find("input[name='goodsName']").val() == "") {
            alert("请输入关键字！");
            return false;
        }
    });
});