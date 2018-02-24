<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 04.02.18
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Questionnaire</title>
</head>
<body>
<form action="/reg" method="post">
    <p>Login:<input type="text" name="login" required><br></p>
    <p>Password:<input type="password" name="password" required><br></p>
    <p>Age:<input type="text" name="age" required><br><br></p>
    <p>Question 1</p>
    <input name="quest1" type="radio" value="Answer 1" checked>Answer 1<br>
    <input name="quest1" type="radio" value="Answer 2">Answer 2<br>
    <p>Question 2</p>
    <input name="quest2" type="radio" value="Answer 1" checked>Answer 1<br>
    <input name="quest2" type="radio" value="Answer 2">Answer 2<br><br>
    <input type="submit"/>
</form>
</body>
</html>