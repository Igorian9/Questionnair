<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 04.02.18
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<% String login = (String) session.getAttribute("user_login");
    String age = (String) session.getAttribute("user_age");
    String answerOne = (String) session.getAttribute("user_answerOne");
    String answerTwo = (String) session.getAttribute("user_answerTwo");
    String count = (String) session.getAttribute("users_count");%>

<% if (login == null || "".equals(login)) { %>
<form action="/login" method="POST">
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="password"><br>
    <button>Log in</button>
</form>
<form action="/quest" method="POST">
    <button name="param" value="reg">Register</button>
</form>
<% } else { %>
<h4>You are logged in as: <%= login %><br>
    Your age: <%= age %><br>
    First answer: <%= answerOne %><br>
    Second answer: <%= answerTwo %><br>
    Now registered <%= count %> users
</h4>
<br>Click this link to <a href="/login?a=exit">logout</a>
<% } %>
</body>
</html>
