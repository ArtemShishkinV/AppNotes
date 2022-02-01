<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование заметки</title>
    <style>
        <jsp:include page="../css/index.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
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
                <form class="" action="editNote?id=${pageContext.request.getParameter("id")}&back=${pageContext.request.getParameter("back")}" method="post">
                    <c:choose>
                        <c:when test="${isOwner}">
                            <div class="main-note-title">
                                <div class="main-note-title-head">
                                    Название
                                </div>
                                <input class="text-input main-note-title-content" type="text" name="title"
                                       value="${note.getTitle()}">
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
                                <textarea class="text-input main-note-text-content" name="text" cols="30"
                                          rows="10">${note.getText()}</textarea>
                            </div>
                        </c:when>
                        <c:otherwise>
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
                                <textarea class="text-input main-note-text-content" name="text" cols="30"
                                          rows="10" disabled>${note.getText()}</textarea>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="main-note-tags" id="tags">
                        <div class="main-note-tags-title">
                            Теги
                        </div>
                        <div class="main-note-tags-content">
                            <c:forEach var="item" items="${tags}">
                                <div class="main-users-item main-note-tags-item">${item.getTitle()}
                                    <input class="main-note-tags-add-plus main-note-tags-add-minus" type="button"
                                           onclick="removeTag(${note.getId()}, ${item.getId()})" value="x">
                                </div>
                            </c:forEach>
                        </div>
                        <c:if test="${!(availableTags.isEmpty())}">
                            <div class="main-note-tags-add main-note-tags-add-create">
                                <div class="select select-edit-note">
                                    <select name="format" id="format">
                                        <option disabled selected>Выберите тэг:</option>
                                        <c:forEach var="item" items="${allTags}">
                                            <option value="${item.getId()}">${item.getTitle()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input class="main-note-tags-add-plus" type="button" onclick="addTag(${note.getId()})"
                                       value="+">
                            </div>
                        </c:if>
                    </div>
                    <div class="main-note-actions">
                        <input class="main-note-actions-action main-note-actions-edit" type="submit" value="Сохранить"/>
                        <a href="${pageContext.request.getParameter("back")}" class="main-note-actions-action main-note-actions-back">
                            Назад
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
