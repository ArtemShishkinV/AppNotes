<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="menu-navigate-list">
    <a href="profile" class="menu-navigate-list-item">
        <svg class="menu-navigate-list-item-icon" width="24" height="24" viewBox="0 0 24 24" fill="none"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M19 22H5C4.44772 22 4 21.5523 4 21V11.357C4.01549 11.1119 4.11964 10.8809 4.293 10.707L11.293 3.70698C11.4806 3.51921 11.7351 3.4137 12.0005 3.4137C12.2659 3.4137 12.5204 3.51921 12.708 3.70698L19.708 10.707C19.8957 10.8943 20.0009 11.1488 20 11.414V21C20 21.5523 19.5523 22 19 22ZM12 5.82798L6 11.828V20H18V11.828L12 5.82798ZM10.5 18.559L7.794 15.859L9.2 14.441L10.5 15.733L14.8 11.441L16.212 12.857L10.5 18.558V18.559Z"
                  fill="#2E3A59"/>
        </svg>
        <div class="block-item">Главная</div>
    </a>
    <c:if test="${!sessionScope.user.role.equals(RoleType.ADMIN.role)}">
    <a href="navigation" class="menu-navigate-list-item">
        <svg class="menu-navigate-list-item-icon" width="24" height="24" viewBox="0 0 24 24" fill="none"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M18.677 19.607L12.962 13.891C10.4196 15.6985 6.91642 15.2564 4.90285 12.8739C2.88929 10.4915 3.03714 6.96361 5.24298 4.75802C7.44824 2.55147 10.9765 2.40298 13.3594 4.41644C15.7422 6.42989 16.1846 9.93347 14.377 12.476L20.092 18.192L18.678 19.606L18.677 19.607ZM9.48498 5.00001C7.58868 4.99958 5.95267 6.3307 5.56745 8.18745C5.18224 10.0442 6.15369 11.9163 7.89366 12.6703C9.63362 13.4242 11.6639 12.8528 12.7552 11.3021C13.8466 9.75129 13.699 7.64734 12.402 6.26402L13.007 6.86402L12.325 6.18402L12.313 6.17202C11.5648 5.4192 10.5464 4.99715 9.48498 5.00001Z"
                  fill="#2E3A59"/>
        </svg>

        <div class="block-item">Навигатор</div>
    </a>

    <a href="subscribe" class="menu-navigate-list-item">
        <svg class="menu-navigate-list-item-icon" width="24" height="24" viewBox="0 0 24 24" fill="none"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M12.8971 21.968C12.366 21.9696 11.8565 21.7586 11.4821 21.382L3.64805 13.547C3.23464 13.1348 3.02266 12.5621 3.06805 11.98L3.56805 5.41401C3.63935 4.4264 4.42625 3.64163 5.41405 3.57301L11.9801 3.07301C12.0311 3.06201 12.0831 3.06201 12.1341 3.06201C12.6639 3.06337 13.1718 3.27399 13.5471 3.64801L21.3821 11.482C21.7573 11.8571 21.9681 12.3659 21.9681 12.8965C21.9681 13.4271 21.7573 13.9359 21.3821 14.311L14.3111 21.382C13.9369 21.7583 13.4277 21.9693 12.8971 21.968ZM12.1331 5.06201L5.56205 5.56201L5.06205 12.133L12.8971 19.968L19.9671 12.898L12.1331 5.06201ZM8.65405 10.654C7.69989 10.6542 6.87847 9.98037 6.69213 9.04458C6.5058 8.10879 7.00646 7.17169 7.88792 6.80639C8.76939 6.44109 9.78615 6.74933 10.3164 7.54259C10.8466 8.33586 10.7426 9.39322 10.0681 10.068C9.69388 10.4443 9.18473 10.6553 8.65405 10.654Z"
                  fill="#2E3A59"/>
        </svg>
        <div class="block-item">Подписки</div>
    </a>
</div>
<div class="menu-notes-list">
    <a href="createNote" class="menu-navigate-list-item">
        <svg class="menu-navigate-list-item-icon" width="24" height="24" viewBox="0 0 24 24" fill="none"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M4.41999 20.579C4.13948 20.5785 3.87206 20.4603 3.68299 20.253C3.49044 20.0475 3.39476 19.7695 3.41999 19.489L3.66499 16.795L14.983 5.48103L18.52 9.01703L7.20499 20.33L4.51099 20.575C4.47999 20.578 4.44899 20.579 4.41999 20.579ZM19.226 8.31003L15.69 4.77403L17.811 2.65303C17.9986 2.46525 18.2531 2.35974 18.5185 2.35974C18.7839 2.35974 19.0384 2.46525 19.226 2.65303L21.347 4.77403C21.5348 4.9616 21.6403 5.21612 21.6403 5.48153C21.6403 5.74694 21.5348 6.00146 21.347 6.18903L19.227 8.30903L19.226 8.31003Z"
                  fill="#2E3A59"/>
        </svg>
        <div class="block-item">Создать заметку</div>
    </a>
    <a href="myNotes" class="menu-navigate-list-item">
        <svg class="menu-navigate-list-item-icon" width="24" height="24" viewBox="0 0 24 24" fill="none"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M18 22H6C4.89543 22 4 21.1046 4 20V3.99999C4 2.89542 4.89543 1.99999 6 1.99999H13C13.009 1.99882 13.018 1.99882 13.027 1.99999H13.033C13.0424 2.00294 13.0522 2.00495 13.062 2.00599C13.1502 2.01164 13.2373 2.02878 13.321 2.05699H13.336H13.351H13.363C13.3815 2.06991 13.3988 2.08429 13.415 2.09999C13.5239 2.14841 13.6232 2.21617 13.708 2.29999L19.708 8.29999C19.7918 8.38477 19.8596 8.48404 19.908 8.59299C19.917 8.61499 19.924 8.63599 19.931 8.65899L19.941 8.68699C19.9689 8.77038 19.9854 8.85717 19.99 8.94499C19.9909 8.95495 19.9932 8.96472 19.997 8.97399V8.97999C19.9986 8.98654 19.9996 8.99324 20 8.99999V20C20 20.5304 19.7893 21.0391 19.4142 21.4142C19.0391 21.7893 18.5304 22 18 22ZM13 3.99999V8.99999H18L13 3.99999Z"
                  fill="#2E3A59"/>
        </svg>
        <div class="block-item">Мои заметки</div>
    </a>
    </c:if>
</div>

<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.rsreu.notes.model.notes.Note" %>
<%@ page import="ru.rsreu.notes.model.users.User" %>
<%@ page import="ru.rsreu.notes.model.roles.RoleType" %>
<%@ page import="ru.rsreu.notes.utils.JspHelper" %>
<%@ page import="java.io.IOException" %>


<%
    User user = (User) session.getAttribute("user");
    ArrayList<Note> notes = (ArrayList<Note>) session.getAttribute("notes");
%>
<c:if test="${!user.getRole().equals(RoleType.USER.getRole())}">
    <div class="menu-subscribes-list">
        <div class="menu-subscribes-list-title">УНИКАЛЬНЫЕ ВОЗМОЖНОСТИ</div>
        <a href="users" class="menu-navigate-list-item menu-subscribes-list-item">
            <img src="" alt="">
            <div class="block-item ">Список пользователей</div>
        </a>

        <c:if test="${user.getRole().equals(RoleType.MODERATOR.getRole())}">
            <a href="tags" class="menu-navigate-list-item menu-subscribes-list-item">
                <img src="" alt="">
                <div class="block-item ">Список тэгов</div>
            </a>
            <a href="blockNotes" class="menu-navigate-list-item menu-subscribes-list-item">
                <img src="" alt="">
                <div class="block-item ">Список заблокированных заметок</div>
            </a>
        </c:if>
    </div>
</c:if>
</body>
</html>