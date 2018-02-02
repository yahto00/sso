<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>HELLO</title>
</head>

<body>
hello....
<div>
    登陆账户:<input type="text" name="id" id="login_id"/>
    账户密码:<input type="text" name="username" id="login_username"/>
    <input type="button" id="ajax" value="ajax"/>
</div>
<script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    $('#ajax').click(function () {
        var id = $('#login_id').val();
        var username = $('#login_username').val();
        $.ajax({
            type: 'POST',
            url: '/sso-demo/hello/testAjax.ajax',
            data: {
                id: id,
                username: username
            },
            dataType: 'json',
            success: function (data) {
                console.log(data);
            },
            error: function (xhr) {
                console.log(xhr.status)
            }
        })
    })
</script>
</body>

</html>