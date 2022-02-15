<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>


<link rel="stylesheet" href="/pub/css/style.css">

<body>


<h1>User Registration</h1>

<form method="GET" action="/indexSubmit">

    Username <input type="text" name="username">
    <br>
    First Name <input type="text" name="firstName">
    <br>
    Dropdown <select name="dropdown">
    <option>Option 1</option>
    <option>Option 2</option>
    <option>Option 3</option>
</select>
    <br>
    <button type="submit">Submit</button>

</form>

</body>

</html>