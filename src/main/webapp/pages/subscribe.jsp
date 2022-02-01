<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подписки</title>
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
            <div class="main-tags-list">
                <c:forEach var="item" items="${tags}">
                    <div class="main-tags-list-item-wrapper">
                        <a href="tagNotes?id=${item.getId()}" class="main-tags-list-item main-tags-list-item-subscribe">
                            <svg class="main-tags-list-item-img" width="30" height="30" viewBox="0 0 30 30"
                                 fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M6.25 9.5C6.25 8.55719 6.25 8.08579 6.54289 7.79289C6.83579 7.5 7.30719 7.5 8.25 7.5H16.5633C17.0239 7.5 17.2542 7.5 17.4558 7.59441C17.6574 7.68882 17.8048 7.86576 18.0997 8.21963L22.683 13.7196C23.1932 14.3318 23.4483 14.638 23.4483 15C23.4483 15.362 23.1932 15.6682 22.683 16.2804L18.0997 21.7804C17.8048 22.1342 17.6574 22.3112 17.4558 22.4056C17.2542 22.5 17.0239 22.5 16.5633 22.5H8.25C7.30719 22.5 6.83579 22.5 6.54289 22.2071C6.25 21.9142 6.25 21.4428 6.25 20.5V9.5Z"
                                      stroke="#2E3A59" stroke-opacity="0.99" stroke-width="2"/>
                            </svg>
                            <div class="main-tags-list-item-title">${item.getTitle()}</div>
                        </a>
                        <a href="subscribeTag?id=${item.getId()}"
                           class="main-note-actions-action main-note-tags-item-button main-note-tags-item-button-subscribe">Отписаться</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>

