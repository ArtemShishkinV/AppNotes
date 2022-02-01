<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>

    <style><jsp:include page="../css/index.css"/></style>
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header-inner">
            <div class="header-title">
                <a href="/" class="logo">Notes </a>
                <div class="header-subtitle"> | Авторизация</div>
            </div>
        </div>
    </div>
</div>
<div class="main">
    <div class="container">
        <form class="block-inner" action="login" method="post">
            <div>
                <div class ="block-item login-item">
                    <div class="block-title"> Login:</div>
                    <div><input class="text-input" type="text" name="login"></div>
                </div>
                <div class ="block-item login-item">
                    <div class="block-title"> Password:</div>
                    <div><input class="text-input" type="password" name="password"></div>
                </div>
                <div class="block-item block-submit">
                    <input class="text-input submit block-submit-button" type="submit" value="Log in"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
