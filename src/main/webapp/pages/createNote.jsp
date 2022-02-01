<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Создание заметки</title>

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
            <div class="block-create">
                <form class="block-create-inner" action="createNote" method="post">
                    <div class="create-table">
                        <div class="create-table-item">
                            <div class="create-table-item-title"> Title:</div>
                            <div><input class="text-input create-table-item-title-value" type="text" name="title"></div>
                        </div>
                        <div class="create-table-item">
                            <div class="create-table-item-title"> Text:</div>
                            <div><textarea class="text-input create-table-text" name="text" cols="30"
                                           rows="10"></textarea></div>
                        </div>
                        <div class="create-table-item">
                            <input class="text-input block-submit-button" type="submit" value="Создать"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
