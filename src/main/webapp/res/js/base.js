//Cookie工具
var cookieUtil = {
    //设置Cookie
    setCookie:
        function setCookie(name,value,days) {
            var date = new Date();
            date.setDate(date.getDate() + days);
            document.cookie = name + "=" + decodeURI(value) + ";expires=" + date.toUTCString();
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
        }
};