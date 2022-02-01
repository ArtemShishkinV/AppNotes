<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Notes</title>

    <style>
        <jsp:include page="../css/index.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
<body>
<div class="header">
    <div class="container">
        <div class="header-inner">
            <a href="#" class="logo">Notes</a>
            <div class="header-right">
                <a class="header-item" href="app/login" id="login">Авторизация</a>
                <a class="header-item" href="app/profile" id="profile">Профиль</a>
                <a class="header-item" href="app/logout" id="logout">Выйти</a>

                <%
                    if (session.getAttribute("user") == null) {
                        out.println("<script>showLogin();</script>");
                    } else {
                        out.println("<script>showProfile();</script>");
                    }
                %>
            </div>
        </div>
    </div>

</div>
</body>
</html>