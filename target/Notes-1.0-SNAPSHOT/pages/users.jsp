<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список пользователей</title>
    <style>
        <jsp:include page="../css/index.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<body onload="refreshPage()">
<%@include file="header.jsp" %>
<div class="main">
    <div class="container">
        <div class="block-inner menu-wrapper">
            <div class="menu">
                <%@include file="menu.jsp" %>
            </div>
            <div class="main-users">
                <c:if test="${sessionScope.user.getRole().equals(RoleType.ADMIN.getRole())}">
                    <a href="createUser" class="main-users-create main-note-actions-action main-note-tags-item-button">
                        Создать пользователя
                    </a>
                </c:if>
                <div class="main-users-list" id="list-users">
                    <c:forEach var="user" items="${users}">
                        <c:if test="${user.getId()!=sessionScope.user.id}">
                            <div class="main-users-item">
                                <div class="main-users-item-block main-users-item-block-name">
                                    <div class="main-users-item-login">Логин: ${user.getLogin()}</div>
                                    <div class="main-users-item-name">Имя: ${user.getName()}</div>
                                </div>
                                <div class="main-users-item-block">
                                    <div class="main-users-item-role">Роль: ${user.getRole().getTitle()}</div>
                                    <div class="main-users-item-blocked">
                                        Статус блок.: ${JspHelper.giveUserStatusBlock(user.isBlocked())}</div>
                                </div>
                                <div class="main-users-item-block main-users-item-block-authorization">
                                    <div class="main-users-item-authorization-title">Статус авторизации:</div>
                                    <div class="main-users-item-authorization-value">
                                            ${JspHelper.giveAuthorizationStatus(user.isStatusAuthorization())}</div>
                                </div>
                                <c:if test="${sessionScope.user.getRole().equals(RoleType.MODERATOR.getRole())}">
                                    <a href="blockUser?id=${user.getId()}">
                                        <c:choose>
                                            <c:when test="${user.isBlocked()}">
                                                <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                                     xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M5 15.2502C5 13.3646 5 12.4218 5.58579 11.836C6.17157 11.2502 7.11438 11.2502 9 11.2502H21C22.8856 11.2502 23.8284 11.2502 24.4142 11.836C25 12.4218 25 13.3646 25 15.2502V20.2502C25 23.0787 25 24.4929 24.1213 25.3716C23.2426 26.2502 21.8284 26.2502 19 26.2502H11C8.17157 26.2502 6.75736 26.2502 5.87868 25.3716C5 24.4929 5 23.0787 5 20.2502V15.2502Z"
                                                          stroke="#2E3A59" stroke-width="2"/>
                                                    <path d="M20.6249 11.25L20.7219 10.4743C21.0456 7.88479 19.8803 5.33431 17.711 3.88381V3.88381C15.1279 2.15673 11.7091 2.3696 9.36025 4.40375L8.33734 5.28961"
                                                          stroke="#2E3A59" stroke-width="2" stroke-linecap="round"/>
                                                    <circle cx="15" cy="18.75" r="2.5" fill="#2E3A59"/>
                                                </svg>
                                            </c:when>
                                            <c:otherwise>
                                                <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                                     xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M5 15.25C5 13.3644 5 12.4216 5.58579 11.8358C6.17157 11.25 7.11438 11.25 9 11.25H21C22.8856 11.25 23.8284 11.25 24.4142 11.8358C25 12.4216 25 13.3644 25 15.25V20.25C25 23.0784 25 24.4926 24.1213 25.3713C23.2426 26.25 21.8284 26.25 19 26.25H11C8.17157 26.25 6.75736 26.25 5.87868 25.3713C5 24.4926 5 23.0784 5 20.25V15.25Z"
                                                          stroke="#2E3A59" stroke-width="2"/>
                                                    <path d="M20 10V8.75C20 8.75 20 8.75 20 8.75C20 5.98858 17.7614 3.75 15 3.75C15 3.75 15 3.75 15 3.75V3.75C15 3.75 15 3.75 15 3.75C12.2386 3.75 10 5.98858 10 8.75C10 8.75 10 8.75 10 8.75V10"
                                                          stroke="#2E3A59" stroke-width="2" stroke-linecap="round"/>
                                                    <ellipse cx="15" cy="18.75" rx="2.5" ry="2.5" fill="#2E3A59"/>
                                                </svg>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </c:if>
                                <c:if test="${sessionScope.user.getRole().equals(RoleType.ADMIN.getRole())}">
                                    <a href="deleteUser?id=${user.getId()}">
                                        <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                            <circle cx="15" cy="15" r="11.25" stroke="#2E3A59" stroke-opacity="0.99"
                                                    stroke-width="2"/>
                                            <path d="M11.2501 18.7496L18.7501 11.2496" stroke="#2E3A59"
                                                  stroke-opacity="0.99"
                                                  stroke-width="2"/>
                                            <path d="M18.75 18.75L11.25 11.25" stroke="#2E3A59" stroke-opacity="0.99"
                                                  stroke-width="2"/>
                                        </svg>
                                    </a>
                                    <a href="editUser?id=${user.getId()}">
                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                            <path d="M4.41999 20.579C4.13948 20.5785 3.87206 20.4603 3.68299 20.253C3.49044 20.0475 3.39476 19.7695 3.41999 19.489L3.66499 16.795L14.983 5.48103L18.52 9.01703L7.20499 20.33L4.51099 20.575C4.47999 20.578 4.44899 20.579 4.41999 20.579ZM19.226 8.31003L15.69 4.77403L17.811 2.65303C17.9986 2.46525 18.2531 2.35974 18.5185 2.35974C18.7839 2.35974 19.0384 2.46525 19.226 2.65303L21.347 4.77403C21.5348 4.9616 21.6403 5.21612 21.6403 5.48153C21.6403 5.74694 21.5348 6.00146 21.347 6.18903L19.227 8.30903L19.226 8.31003Z"
                                                  fill="#2E3A59"/>
                                        </svg>
                                    </a>
                                </c:if>
                            </div>

                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
