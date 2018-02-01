<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>hydra单点登录系统</title>
</head>

<body>
<div>
    登陆账户:<input type="text" id="login_account"/>
    账户密码:<input type="password" id="login_password"/>
    <input type="button" value="login" id="login_button"/>
</div>
<script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    $.ajaxSetup({complete:function(xhr,status){
            //若HEADER中含有REDIRECT说明后端想重定向
            if("REDIRECT" == xhr.getResponseHeader("REDIRECT")){
                //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
                console.log(xhr.getResponseHeader("CONTENTPATH"));
                window.location.href = xhr.getResponseHeader("CONTENTPATH");
            }
        }});

    $("#login_button").click(function () {
        //获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg); //匹配目标参数
            if (r != null) return unescape(r[2]);
            return null; //返回参数值
        }

        var backUrl = getUrlParam('backUrl');
        var applicationCode = getUrlParam('applicationCode');
        console.log(backUrl);
        console.log(applicationCode);
        var account = $("#login_account").val();
        console.log(account);
        var password = $("#login_password").val();
        console.log(password);
        $.ajax({
            type: "POST",
            url: "/sso-server/login",
            data: {
                account: account,
                password: password,
                applicationCode: applicationCode,
                backUrl: backUrl
            },
            // dataType: "json",
            success: function (data) {
                if (data.state) {
                    $("#test").html(data.msg);

                } else {
                    $("#test").html("验证未通过:" + data.msg);
                }
            },
            error: function (jqXHR) {
                $("#test").html("发生错误:" + jqXHR.status);
            }
        });
    })
</script>
</body>

</html>