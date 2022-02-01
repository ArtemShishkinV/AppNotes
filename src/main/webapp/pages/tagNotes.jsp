<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.rsreu.notes.model.notes.Note" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заметки по тегу</title>

    <style>
        <jsp:include page="../css/index.css"/>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="main">
    <div class="container">
        <div class="block-inner">
            <div class="menu">
                <%@include file="menu.jsp" %>
            </div>
            <div class="main-mynotes-list">
                <c:forEach var="note" items="${notes}">
                    <div class="main-mynotes-item">
                        <div class="main-mynotes-item-title main-tag-notes-item-title">${note.getTitle()}</div>
                        <div class="main-mynotes-item-text">${note.getText()}</div>
                        <div class="main-mynotes-item-footer">
                            <c:if test="${user.getRole().equals(RoleType.MODERATOR.getRole())}">
                                <a href="editNote?id=${note.getId()}&back=${back}" class="main-mynotes-item-footer-item">
                                    <div class="main-mynotes-item-footer-item-title">Редактировать</div>
                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M4.41999 20.579C4.13948 20.5785 3.87206 20.4603 3.68299 20.253C3.49044 20.0475 3.39476 19.7695 3.41999 19.489L3.66499 16.795L14.983 5.48103L18.52 9.01703L7.20499 20.33L4.51099 20.575C4.47999 20.578 4.44899 20.579 4.41999 20.579ZM19.226 8.31003L15.69 4.77403L17.811 2.65303C17.9986 2.46525 18.2531 2.35974 18.5185 2.35974C18.7839 2.35974 19.0384 2.46525 19.226 2.65303L21.347 4.77403C21.5348 4.9616 21.6403 5.21612 21.6403 5.48153C21.6403 5.74694 21.5348 6.00146 21.347 6.18903L19.227 8.30903L19.226 8.31003Z"
                                              fill="#2E3A59"/>
                                    </svg>
                                </a>
                            </c:if>
                            <a href="selectNote?id=${note.getId()}&back=${back}"
                               class="main-mynotes-item-footer-item main-tag-notes-item-footer">
                                <div class="main-mynotes-item-footer-item-title">Перейти</div>
                                <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path d="M6.16117 23.8388C7.90932 25.587 10.1366 26.7775 12.5614 27.2598C14.9861 27.7421 17.4995 27.4946 19.7835 26.5485C22.0676 25.6024 24.0199 24.0002 25.3934 21.9446C26.7669 19.889 27.5 17.4723 27.5 15C27.5 12.5277 26.7669 10.111 25.3934 8.05537C24.0199 5.99976 22.0676 4.3976 19.7835 3.45151C17.4995 2.50541 14.9861 2.25787 12.5614 2.74018C10.1366 3.2225 7.90932 4.41301 6.16117 6.16116"
                                          stroke="#2E3A59" stroke-width="2"/>
                                    <path d="M18.75 15L19.5309 14.3753L20.0306 15L19.5309 15.6247L18.75 15ZM3.75 16C3.19772 16 2.75 15.5523 2.75 15C2.75 14.4477 3.19772 14 3.75 14V16ZM14.5309 8.1253L19.5309 14.3753L17.9691 15.6247L12.9691 9.3747L14.5309 8.1253ZM19.5309 15.6247L14.5309 21.8747L12.9691 20.6253L17.9691 14.3753L19.5309 15.6247ZM18.75 16H3.75V14H18.75V16Z"
                                          fill="#2E3A59"/>
                                </svg>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>

