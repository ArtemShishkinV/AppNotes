<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заметка</title>
    <style>
        <jsp:include page="../css/index.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="../js/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main">
    <div class="container">
        <div class="block-inner menu-wrapper">
            <div class="menu">
                <%@include file="menu.jsp" %>
            </div>
            <div class="main-note">
                <div class="main-note-title">
                    <div class="main-note-title-head">
                        Название
                    </div>
                    <input class="text-input main-note-title-content" type="text" name="title"
                           value="${note.getTitle()}" disabled>
                </div>
                <div class="main-note-title">
                    <div class="main-note-title-head">
                        Автор
                    </div>
                    <input class="text-input main-note-title-content" type="text" name="title"
                           value="${pageContext.request.getAttribute("noteOwner")}" disabled>
                </div>
                <div class="main-note-text">
                    <div class="main-note-text-title">
                        Текст заметки
                    </div>
                    <textarea class="text-input main-note-text-content" name="text" cols="30" rows="10"
                              disabled>${note.getText()}</textarea>
                </div>
                <div class="main-note-tags">
                    <div class="main-note-tags-title">
                        Теги
                    </div>
                    <div class="main-note-tags-content">
                        <c:forEach var="item" items="${tags}">
                            <div class="main-users-item main-note-tags-item">
                                <div class="main-note-tags-item-title">
                                        ${item.getTitle()}
                                </div>
                                <a href="subscribeTag?id=${item.getId()}"
                                   class="main-note-actions-action main-note-tags-item-button">
                                        ${JspHelper.giveSubscribeStatus(subscribeTags.contains(item))}
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="main-note-actions">
                    <c:if test="${isOwner}">
                        <a href="editNote?id=${pageContext.request.getParameter("id")}&back=${pageContext.request.getParameter("back")}"
                           class="main-note-actions-action main-note-actions-edit">
                            Редактировать
                        </a>
                    </c:if>
                    <c:if test="${user.getRole().equals(RoleType.MODERATOR.getRole())}">
                        <a href="blockNote?id=${note.getId()}&back=${pageContext.request.getParameter("back")}"
                           class="main-note-actions-action main-note-actions-edit">
                            Заблокировать
                        </a>
                    </c:if>
                    <a href="${pageContext.request.getParameter("back")}"
                       class="main-note-actions-action main-note-actions-back">
                        Назад
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>

