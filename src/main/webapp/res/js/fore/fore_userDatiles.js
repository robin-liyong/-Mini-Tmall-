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

    $("#user_nickname").focus(function () {
        $(this).next().text("输入昵称");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    });

    $("#user_nickname").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    $("#user_realname").focus(function () {
        $(this).next().text("输入真实姓名");
        $(this).next().css("display","inline-block");
        $(this).next().css("color","#00A0E9");
        $(this).css("border","1px solid #3879D9");
    })

    $("#user_realname").blur(function () {
        $(this).next().css("display","none");
        $(this).css("border-color","#cccccc");
    })

    //非空验证
    $("#register_sub").click(function () {
        var user_nickname = $("#user_nickname").val();
        var user_realname = $("#user_realname").val();
        if(user_nickname == null || user_nickname == ""){
            $("#user_nickname").next().text("请输入昵称");
            $("#user_nickname").next().css("display","inline-block");
            $("#user_nickname").next().css("color","red");
            $("#user_nickname").css("border","1px solid red");
            return false;
        }else if(user_realname == null || user_realname == ""){
            $("#user_realname").next().text("请输入真实姓名");
            $("#user_realname").next().css("display","inline-block");
            $("#user_realname").next().css("color","red");
            $("#user_realname").css("border","1px solid red");
            return false
        }else{
            return true;
        }
    })
});

//图片上传
function uploadImage(fileDom) {
    //获取文件
    var file = fileDom.files[0];
    //判断类型
    var imageType = /^image\//;
    if (file === undefined || !imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //判断大小
    if (file.size > 512000) {
        alert("图片大小不能超过500K！");
        return;
    }
    //清空值
    $(fileDom).val('');
    var formData = new FormData();
    formData.append("file", file);
    //上传图片
    $.ajax({
        url: "/tmall/user/uploadUserHeadImage",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            if (data.success) {
                $(fileDom).prev("img").attr("src","/tmall/res/images/item/userProfilePicture/"+data.fileName);
                $("#user_profile_picture_src_value").val(data.fileName);
            } else {
                alert("图片上传异常！");
            }
        },
        beforeSend: function () {
        },
        error: function () {

        }
    });
}