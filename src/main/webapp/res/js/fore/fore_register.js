$(function () {
    //刷新下拉框
    $('#select_user_address_province').selectpicker('refresh');
    $('#select_user_address_city').selectpicker('refresh');
    $('#select_user_address_district').selectpicker('refresh');
    //改变订单信息时
    $('#select_user_address_province').change(function () {
        $.ajax({
            type: "GET",
            url: "/tmall/address/" + $(this).val(),
            data: null,
            dataType: "json",
            success: function (data) {
                $(".loader").hide();
                if (data.success) {
                    $("#select_user_address_city").empty();
                    $("#select_user_address_district").empty();
                    for (var i = 0; i < data.addressList.length; i++) {
                        var address_id = data.addressList[i].address_areaId;
                        var address_name = data.addressList[i].address_name;
                        $("#select_user_address_city").append("<option value='" + address_id + "'>" + address_name + "</option>")
                    }
                    for (var j = 0; j < data.childAddressList.length; j++) {
                        var childAddress_id = data.childAddressList[j].address_areaId;
                        var childAddress_name = data.childAddressList[j].address_name;
                        $("#select_user_address_district").append("<option value='" + childAddress_id + "'>" + childAddress_name + "</option>")
                    }
                    $('#select_user_address_city').selectpicker('refresh');
                    $("#select_user_address_district").selectpicker('refresh');
                    $("span.address-province").text($("#select_user_address_province").find("option:selected").text());
                    $("span.address-city").text($("#select_user_address_city").find("option:selected").text());
                    $("span.address_district").text($("#select_user_address_district").find("option:selected").text());
                } else {
                    alert("加载地区信息失败，请刷新页面再试！")
                }
            },
            beforeSend: function () {
                $(".loader").show();
            },
            error: function () {
                alert("加载地区信息失败，请刷新页面再试！")
            }
        });
    });
    $("#select_user_address_city").change(function () {
        $.ajax({
            type: "GET",
            url: "/tmall/address/" + $(this).val(),
            data: null,
            dataType: "json",
            success: function (data) {
                $(".loader").hide();
                if (data.success) {
                    $("#select_user_address_district").empty();
                    for (var i = 0; i < data.addressList.length; i++) {
                        var address_id = data.addressList[i].address_areaId;
                        var address_name = data.addressList[i].address_name;
                        $("#select_user_address_district").append("<option value='" + address_id + "'>" + address_name + "</option>")
                    }
                    $('#select_user_address_district').selectpicker('refresh');
                    $("span.address-city").text($("#select_user_address_city").find("option:selected").text());
                    $("span.address_district").text($("#select_user_address_district").find("option:selected").text());
                } else {
                    alert("加载地区信息失败，请刷新页面再试！")
                }
            },
            beforeSend: function () {
                $(".loader").show();
            },
            error: function () {
                alert("加载地区信息失败，请刷新页面再试！")
            }
        });
    });
    $("#select_user_address_district").change(function () {
        $("span.address_district").text($(this).find("option:selected").text());
    });
    $("#textarea_details_address").bind('input propertychange', function () {
        $(".address_details").text($(this).val());
    });
    $("#input_user_receiver").bind('input propertychange', function () {
        $(".user-name").text($(this).val());
    });
    $("#input_user_phone").bind('input propertychange', function () {
        $(".user-phone").text($(this).val());
    });
    $("input,textarea").focus(function () {
        styleUtil.specialBasicErrorHide($(this).prev("span").prev("label"));
    });

    //用户名input获取光标
    $("#user_name").focus(function () {
        $(this).next().text("输入用户名");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    })
  //用户名input离开光标
    $("#user_name").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    //密码input获取光标
    $("#user_password").focus(function () {
        $(this).next().text("输入密码");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    })

    //密码input离开光标
    $("#user_password").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    //再次输入密码input获取光标
    $("#user_password_one").focus(function () {
        $(this).next().text("再次输入密码");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    })

    //再次输入密码input离开光标
    $("#user_password_one").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    //昵称input获取光标
    $("#user_nickname").focus(function () {
        $(this).next().text("输入昵称");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    })

    //昵称input离开光标
    $("#user_nickname").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    //非空验证
    $("#register_sub").click(function () {
        //用户名
        var user_name = $("[name=user_name]").val();
        //密码
        var user_password = $("[name=user_password]").val();
        //确认密码
        var user_password_one = $("[name=user_password_one]").val();
        //昵称
        var user_nickname = $("[name=user_nickname]").val();

        //验证密码的格式 包含数字和英文字母
        var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
        if(user_name == null || user_name == ""){
            $("#user_name").css("border","1px solid red");
            $("#user_name").next().text("请输入用户名");
            $("#user_name").next().css("display","inline-block");
            $("#user_name").next().css("color","red");
            return false;
        }else  if(user_password == null || user_password == ""){
            $("#user_password").next().text("请输入密码");
            $("#user_password").next().css("display","inline-block");
            $("#user_password").next().css("color","red");
            $("#user_password").css("border","1px solid red");
            return false;
        }else  if(user_password_one == null || user_password_one == ""){
            $("#user_password_one").next().text("请输入密码");
            $("#user_password_one").next().css("display","inline-block");
            $("#user_password_one").next().css("color","red");
            $("#user_password_one").css("border","1px solid red");
            return false;
        }else if(!reg.test(user_password)){
            $("#user_password").next().text("密码格式必须包含数字和字母");
            $("#user_password").next().css("display","inline-block");
            $("#user_password").next().css("color","red");
            $("#user_password").css("border","1px solid red");
            return false;
        } else if(user_password != user_password_one){
            $("#user_password_one").next().text("两次输入密码不相同");
            $("#user_password_one").next().css("display","inline-block");
            $("#user_password_one").next().css("color","red");
            $("#user_password_one").css("border","1px solid red");
            return false;
        }else if(user_nickname == null || user_nickname == ""){
            $("#user_nickname").next().text("请输入昵称");
            $("#user_nickname").next().css("display","inline-block");
            $("#user_nickname").next().css("color","red");
            $("#user_nickname").css("border","1px solid red");
            return false;
        }else{
            return true;
        }
    })


});

