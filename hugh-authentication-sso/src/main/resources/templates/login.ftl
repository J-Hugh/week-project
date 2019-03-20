<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hugh login</title>
</head>
<body>
<form action="/sso/login" method="post">
    账号:<input type="text" name="userAccount" value="${userAccount!}">
    密码:<input type="password" name="password" value="${password!}">
    <input type="hidden" name="returnUrl" value="${returnUrl!}">
    <button type="submit">登录</button>
</form>
<p>${error!}</p>
</body>
</html>