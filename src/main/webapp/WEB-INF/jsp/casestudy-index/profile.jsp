<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container">
        <h2 class="display-4 text-darkblue text-center">My profile</h2>
        <article class="m-auto col col-xl-5 col-lg-6 col-md-8 shadow-lg rounded15px p-5 mt-4">

            <form method="GET" action="/editUser?id=id" class="row">
                <input type="hidden" name="id" value="${user.id}">

                <label class="ms-3" for="username">Username</label>
                <input type="text" class="form-control text-secondary" id="username" value="${user.username}" readonly>

                <label class="mt-3 ms-3" for="email">Email</label>
                <input type="text" class="form-control text-secondary" id="email" value="${user.email}" readonly>

                <label class="mt-3 ms-3" for="firstName">First Name</label>
                <input type="text" class="form-control text-secondary" id="firstName" value="${user.firstName}" readonly>

                <label class="mt-3 ms-3" for="lastName">Last Name</label>
                <input type="text" class="form-control text-secondary" id="lastName" value="${user.lastName}" readonly>

                <label class="mt-3 ms-3" for="phone">Phone</label>
                <input type="text" class="form-control text-secondary" id="phone" value="${user.phone}" readonly>

                <label class="mt-3 ms-3" for="location">Location's address</label>
                <input type="text" class="form-control text-secondary" id="location" value="${user.defaultLocation.address}" readonly>

                <button class="mt-3 btn btn-dark btn-lg btn-block" type="submit">Edit Profile</button>
                <div class="w-75 m-auto mt-4 fs-5">
                    <a class="mt-5 text-muted" href="/case">Go to Main page</a>
                </div>
            </form>
        </article>


    </div>
</main>

<jsp:include page="../include/footer.jsp" />