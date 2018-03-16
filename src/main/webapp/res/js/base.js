//Cookie工具
var cookieUtil = {
    //设置Cookie
    setCookie:
        function setCookie(name,value,days) {
            var date = new Date();
            date.setDate(date.getDate() + days);
            document.cookie = name + "=" + decodeURI(value) + ";expires=" + date.toUTCString();
            return this;
        },
    //获取Cookie
    getCookie:
        function getCookie(name) {
            var arr;
            var reg = new RegExp("(^| )" + name + "=([^;]*)($|;)");
            if (arr = document.cookie.match(reg)) {
                return decodeURIComponent(arr[2]);
            }
            return null;
        },
    //移除Cookie
    removeCookie:
        function removeCookie(name) {
            cookieUtil.setCookie(name, "", -1);
            return this;
        }
};
//样式统一工具
var styleUtil = {
    //显示表单验证错误提示
    errorShow:
        function errorShow(obj,text) {
            obj.text(text);
            if (obj.css("opacity") !== "1") {
                obj.animate({
                    left: "0",
                    opacity: 1
                }, 200);
            } else {
                obj.css("opacity", "0.5").animate({
                    opacity: 1
                }, 100);
            }
            return this;
        },
    //隐藏表单验证错误提示
    errorHide:
        function errorHide(obj) {
            if(obj.css("opacity") !== "0"){
                obj.animate({
                    left: "20px",
                    opacity: 0
                }, 200);
            }
            return this;
        }
};