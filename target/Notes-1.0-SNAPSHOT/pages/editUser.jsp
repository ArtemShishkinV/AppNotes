<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователя</title>
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
            <form class="block-inner" action="editUser?id=${editUser.getId()}" method="post">
                <div class="block-item-wrapper">
                    <div class="block-item login-item">
                        <div class="block-title"> Name:</div>
                        <div><input class="text-input" type="text" name="name" value="${editUser.getName()}"></div>
                    </div>
                    <div class="block-item login-item">
                        <div class="block-title"> Login:</div>
                        <div><input class="text-input" type="text" name="login" value="${editUser.getLogin()}"></div>
                    </div>
                    <div class="block-item login-item">
                        <div class="block-title"> Password:</div>
                        <div><input class="text-input" type="text" name="password" value="${editUser.getPassword()}">
                        </div>
                    </div>
                    <div class="select select-user-role">
                        <select name="format" id="format">
                            <c:forEach var="item" items="${requestScope.roles}">
                                <c:choose>
                                    <c:when test="${editUser.getRole().equals(item)}">
                                        <option value="${item.getId()}" selected>${item.getTitle()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.getId()}">${item.getTitle()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="block-item login-item block-submit">
                        <input class="text-input block-submit-button" type="submit" value="Сохранить"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


