<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список заблокированных тегов</title>
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
            <div class="main-mynotes-list">
                <c:forEach var="note" items="${notes}">
                    <div class="main-users-item main-tags-item main-block-notes-item">
                        <div class="main-users-item-login main-blocked-notes-title">
                            Название: ${note.getTitle()}
                        </div>
                        <a href="blockNote?id=${note.getId()}&back=blockNotes">
                            <svg width="30" height="30" viewBox="0 0 30 30" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M5 15.2502C5 13.3646 5 12.4218 5.58579 11.836C6.17157 11.2502 7.11438 11.2502 9 11.2502H21C22.8856 11.2502 23.8284 11.2502 24.4142 11.836C25 12.4218 25 13.3646 25 15.2502V20.2502C25 23.0787 25 24.4929 24.1213 25.3716C23.2426 26.2502 21.8284 26.2502 19 26.2502H11C8.17157 26.2502 6.75736 26.2502 5.87868 25.3716C5 24.4929 5 23.0787 5 20.2502V15.2502Z"
                                      stroke="#2E3A59" stroke-width="2"/>
                                <path d="M20.6249 11.25L20.7219 10.4743C21.0456 7.88479 19.8803 5.33431 17.711 3.88381V3.88381C15.1279 2.15673 11.7091 2.3696 9.36025 4.40375L8.33734 5.28961"
                                      stroke="#2E3A59" stroke-width="2" stroke-linecap="round"/>
                                <circle cx="15" cy="18.75" r="2.5" fill="#2E3A59"/>
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
