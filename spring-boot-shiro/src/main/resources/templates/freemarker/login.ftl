<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div>登录界面</div>
<div>
    <form action="login" method="post">
        <ul>
            <li>用户名：<input type="text" name="username" value=""/></li>
            <li>密码：<input type="text" name="password" value=""/></li>
            <li>记住我<input type="checkbox" name="rememberMe" value="1"/></li>
            <li><input type="submit" value="登录"/></li>
        </ul>
        <div class="error">${error}</div>
    </form>
</div>

</body>
</html>