<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.01.2018
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="css.css">
    <title>Person</title>
</head>
<body>

    <c:if test="${sessionScope.person.name == 'ADMIN'}">
        <c:forEach items="${sessionScope.persons}" var="pers">
            <form action="ChangePerson" method="POST">
                Имя: <input type="text" name="name" value=${pers.name} readonly> <br>
                Пароль: ${pers.pass} <br>
                Количество входов с неверным паролем: ${pers.errorCount} <br>
                Блокировка:
                <c:choose>
                    <c:when test="${pers.isBlocked}">
                        Да<input type="radio" name="isBlocked" value="1" checked> Нет<input type="radio" name="isBlocked" value="0">
                    </c:when>
                    <c:otherwise>
                        Да<input type="radio" name="isBlocked" value="1"> Нет<input type="radio" name="isBlocked" value="0" checked>
                    </c:otherwise>
                </c:choose>
                <br>

                Ограничение на пароль:
                <c:choose>
                    <c:when test="${pers.limitation}">
                        Да<input type="radio" name="limitation" value="1" checked> Нет<input type="radio" name="limitation" value="0">
                    </c:when>
                    <c:otherwise>
                        Да<input type="radio" name="limitation" value="1"> Нет<input type="radio" name="limitation" value="0" checked>
                    </c:otherwise>
                </c:choose>
                <br>
                <button type="submit">Обновить</button>
                <br>
                ____________________________________________________
                <br>
            </form>
        </c:forEach>
        ____________________________________________________<br>

        <form action="AddNewUser" method="post">
            Добавить нового пользователя:<br>
            <input type="text" name="name">
            <button type="submit">Добавить</button>
        </form>
        ____________________________________________________<br>
    </c:if>

        <form action="ChangePass" method="post">
            <input type="text" name="name" value=${sessionScope.person.name} hidden>
            Старый пароль: <input type="password" name="oldPass"><br>
            Новый пароль: <input type="password" name="newPass"><br>
            <button type="submit">Изменить пароль</button><br><br>
            <c:if test="${sessionScope.person.limitation}">
                Винимание! Действует ограничение на пароль от администратора. (Пароль должен быть отличен от логина)
            </c:if>
        </form>

</body>
</html>
