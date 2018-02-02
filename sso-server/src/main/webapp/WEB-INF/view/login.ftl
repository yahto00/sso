<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>hydra单点登录系统</title>
</head>

<body>
<div>
    <form name="submit" action="/sso-server/login" method="post">
        <input type="hidden" name="backUrl" id="back_url"/>
        <input type="hidden" name="applicationCode" id="application_code"/>
        登陆账户:<input type="text" name="account" id="login_account"/>
        账户密码:<input type="password" name="password" id="login_password"/>
        <input type="submit" value="login" id="login_button"/>
    </form>
</div>
<script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
    var backUrl = getUrlParam('backUrl'),
            applicationCode = getUrlParam('applicationCode');
    $('#back_url').val(backUrl);
    $('#application_code').val(applicationCode);

</script>
</body>

</html>