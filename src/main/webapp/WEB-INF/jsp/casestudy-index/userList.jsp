<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main class="marginForFooter">
    <form method="GET" action="/case/admin">
        <div class="m-auto col col-xl-5 col-md-8 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="search" placeholder="Get list of users by first name or last name LIKE (ignore case)...Type % to see all users">
            <button class="btn btn-dark btn-block" type="submit">Search</button>
        </div>
    </form>
    <article class="m-auto col col-xl-8 col-md-10 shadow-lg rounded15px p-5 mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userListKey}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><a class="btn btn-secondary" href="/case/editUser?id=${user.id}">Edit</a></td>
                    <td><a class="btn btn-warning" href="">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>
</main>

<jsp:include page="../include/footer.jsp" />