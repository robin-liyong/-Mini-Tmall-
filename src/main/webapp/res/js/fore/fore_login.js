$(function () {
    //二维码动画
    $("#qrcodeA").hover(
        function () {
            $(this).stop().animate({left: "13px"}, 450, function () {
                $("#qrcodeB").stop().animate({opacity: 1}, 300)
            });
        }
        , function () {
            $("#qrcodeB").css("opacity", "0");
            $(this).stop().animate({left: "80px"}, 450);
        });
    //登录方式切换
    $("#loginSwitch").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        if ($(".pwdLogin").css("display") === "block") {
            $(".pwdLogin").css("display", "none");
            $(".qrcodeLogin").css("display", "block");
            messageSpan.text("密码登录在这里");
            $(this).removeClass("loginSwitch").addClass("loginSwitch_two");
        } else {
            $(".pwdLogin").css("display", "block");
            $(".qrcodeLogin").css("display", "none");
            messageSpan.text("扫码登录更安全");
            $(this).removeClass("loginSwitch_two").addClass("loginSwitch");
        }
    });
    $("#pwdLogin").click(function () {
        var messageSpan = $(".loginMessageMain").children("span");
        $(".pwdLogin").css("display", "block");
        $(".qrcodeLogin").css("display", "none");
        messageSpan.text("扫码登录更安全");
        $("#loginSwitch").removeClass("loginSwitch_two").addClass("loginSwitch");
    });
    //登录验证
    $(".loginForm").submit(function () {
        var yn = true;
        $(this).find(":text,:password").each(function () {
            if ($.trim($(this).val()) == "") {
                $(this).css("border-color", "#FF0036");
                yn = false;
                return yn;
            }
        });
        if (yn) {
            $.ajax({
                type: "POST",
                url: "fore_login.action",
                data: {
                    "name": $.trim($("#name").val()),
                    "password": $.trim($("#password").val())
                },
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        location.href = "fore_home.action";
                    } else {
                        $(".loginButton").val("登 录");
                        $("#error_message_p").text(data.msg);
                    }
                },
                error: function (data) {
                    if (!data.success) {
                        $(".loginButton").val("登 录");
                        $("#error_message_p").text(data.msg);
                    }
                },
                beforeSend: function () {
                    $(".loginButton").val("正在登录...");
                }
            });
        }
        return false;
    });
    $(".loginForm :text,.loginForm :password").focus(function () {
        $(this).css("border-color", "#CBCBCB");
    });
});