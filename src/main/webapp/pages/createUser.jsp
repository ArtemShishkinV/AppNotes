<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пользователя</title>
    <style>
        <jsp:include page="../css/index.css"/>

    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main">
    <div class="container">
        <div class="block-inner menu-wrapper">
            <div class="menu">
                <%@include file="menu.jsp" %>
            </div>
            <form class="block-inner" action="createUser" method="post">
                <div class="block-item-wrapper">
                    <div class ="block-item login-item">
                        <div class="block-title"> Имя:</div>
                        <div><input class="text-input" maxlength="16" type="text" name="name"></div>
                    </div>
                    <div class ="block-item login-item">
                        <div class="block-title"> Логин:</div>
                        <div><input class="text-input" maxlength="16" type="text" name="login"></div>
                    </div>
                    <div class ="block-item login-item block-item-password">
                        <div class="block-title"> Пароль:</div>
                        <div><input class="text-input" maxlength="20" type="password" name="password"></div>
                    </div>
                    <div class="select">
                        <select name="format" id="format">
                            <option disabled selected>Выберите роль:</option>
                            <c:forEach var="item" items="${requestScope.roles}">
                                <option value="${item.getId()}">${item.getTitle()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class ="block-item login-item block-submit">
                        <input class="text-input block-submit-button block-submit-button-register" type="submit" value="Зарегистрировать"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

