<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список тегов</title>
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
            <div class="main-mynotes-list main-moderator-tags-list">
                <form class="main-users-item main-tags-item" action="tags" method="post">
                    <div class="main-users-item-login main-tags-add-title">
                        Название: <input class="text-input create-tag-input" type="text" name="title">
                    </div>
                    <input class="main-tags-add-item main-users-item" type="submit" value="Добавить тег"/>
                </form>
                <c:forEach var="tag" items="${tags}">
                    <div class="main-users-item main-tags-item">
                        <div class="main-users-item-login">
                            Название: ${tag.getTitle()}
                        </div>
                        <a href="deleteTag?id=${tag.getId()}">
                            <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <circle cx="15" cy="15" r="11.25" stroke="#2E3A59" stroke-opacity="0.99"
                                        stroke-width="2"/>
                                <path d="M11.2501 18.7496L18.7501 11.2496" stroke="#2E3A59" stroke-opacity="0.99"
                                      stroke-width="2"/>
                                <path d="M18.75 18.75L11.25 11.25" stroke="#2E3A59" stroke-opacity="0.99"
                                      stroke-width="2"/>
                            </svg>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

</body>
</html>
