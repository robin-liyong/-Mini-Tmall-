$(function () {
    //打赏输入验证
    $(".messageForm").submit(function () {
        var yn = true;
        if ($.trim($("#name").val()) === "") {
            yn = false;
            alert("请输入您的昵称");
            return yn;
        }
        if ($.trim($("#message").val()) === "") {
            $("#message").val("无");
        }
        if (yn) {
            $.ajax({
                type: "POST",
                url: "/tmall/reward/add",
                data: {"name": $.trim($("#name").val()), "message": $.trim($("#message").val())},
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        alert("谢谢您的支持，我们会尽快审核！");
                        $("#name").val("").attr("disabled",true);
                        $("#message").val("").attr("disabled",true);
                        $("#checkbox_is_anonymous").attr("disabled",true);
                        $("#reward_submit").attr("disabled",true);
                        //自定义动画
                        $('body,html').animate({
                            scrollTop: 0
                        }, 500);
                    }
                },
                error: function (data) {
                    alert("抱歉，服务器异常，请刷新页面再试");
                },
                beforeSend: function () {
                    $(".reward_submit").val("正在提交...");
                }
            });
        }
        return false;
    });

    //匿名选项
    $("#checkbox_is_anonymous").change(function () {
        if($(this).prop("checked")){
            $("#name").val("匿名").attr("disabled",true);
        } else {
            $("#name").val("").attr("disabled",false);
        }
    });
});
