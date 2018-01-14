<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.01.2018
  Time: 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css.css">
    <title>Index</title>
</head>
<body>

<form action="Auth" method="post">
    Логин: <input type="text" value="" name="name"><br>
    Пароль: <input type="password" value="" name="pass"><br>
    <button type="submit">Авторизоваться</button><br>
</form>
<form action="about.html" method="get">
    <button type="submit">Справка</button><br>
</form>

</body>
</html>
