<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.marinagaisina.casestudy.practicing.UtilClass" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>UdemyPracticing</title>
</head>
<body>
<h3>Calling a Java Class from JSP:</h3>
Let's make it in a lower case: "UPPERCASE LETTERS": <%= UtilClass.makeItLower("UPPERCASE LETTERS") %>

<h3>JSP Built-In Objects:</h3>
Request user agent: <%= request.getHeader("User-Agent") %><br>
Request language: <%= request.getLocale() %>

<div style="float: right">
<h3>Using style and java.util.Date() example:</h3>
Last updated: <%= new java.util.Date() %>
</div>
<h1>User Registration</h1>

<div style="border: #045457 2px solid; margin: 20px; padding: 10px; width: fit-content;">
    <form method="GET" action="/udemy/submit">

        Username <input type="text" name="formUserName">
        <br>
        Full Name <input type="text" name="formFullName">
        <br>
        Dropdown <select name="formDropdown">
                    <option>Option 1</option>
                    <option>Option 2</option>
                    <option>Option 3</option>
                 </select><br>
        <input type="checkbox" name="favoriteLanguage" value="Java"> Java
        <input type="checkbox" name="favoriteLanguage" value="C#"> C#
        <input type="checkbox" name="favoriteLanguage" value="PHP"> PHP
        <input type="submit" value="Submit" />

    </form>
</div>

<h1>To Do List (using session)</h1>
<div style="border: #045457 2px solid; margin: 20px; padding: 10px; width: 50%;">
    <form method="GET" action="/udemy">
        Add new item: <input type="text" name="itemNameForm"><br>
        <input type="submit" value="Submit" />
    </form>
    <br>
    Item entered: ${itemParam}<br>
    Getting parameter, using a scriplet (request.getParameter): <%= request.getParameter("itemNameForm") %>
    <ol>
        <c:forEach items="${toDoListAttribute}" var="item">
            <li>${item}</li>
        </c:forEach>
    </ol>
</div>
<h1>Beans</h1>
    <jsp:useBean id="studentsList" scope="request" type="java.util.List"/>
    <c:forEach items="${studentsList}" var="student">
        <li>${student}</li>
    </c:forEach>
</body>
</html>