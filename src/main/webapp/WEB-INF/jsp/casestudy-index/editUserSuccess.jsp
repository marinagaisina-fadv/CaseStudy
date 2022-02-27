<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container text-darkblue">
        <h2 class="display-7 text-darkblue m-auto text-center col col-xl-7 col-md-10">
            <span class="text-bold">${formBeanKey.firstName}</span>, your profile with username ${formBeanKey.username} has been successfully edited!
        </h2>
        <article id="article-register" class="m-auto col col-xl-5 col-md-7 shadow-lg rounded15px p-5 mt-4">
            <form action="#" class="row">
                <input type="hidden" name="id" value="${formBeanKey.id}">

                <label class="text-darkblue text-center" for="address">Your default location</label>
                <input type="text" class="form-control" name="locationAddress" value="${formBeanKey.locationAddress}" readonly id="address">

                <label class="mt-3 text-darkblue text-center" for="email">Your email</label>
                <input type="text" class="form-control" name="email" value="${formBeanKey.email}" readonly id="email">

                <label class="mt-3 text-darkblue text-center" for="firstName">Your first name</label>
                <input type="text" class="form-control" name="firstName" value="${formBeanKey.firstName}" readonly id="firstName">

                <label class="mt-3 text-darkblue text-center" for="lastName">Your last name</label>
                <input type="text" class="form-control" name="lastName" value="${formBeanKey.lastName}" readonly id="lastName">

                <label class="mt-3 text-darkblue text-center" for="phone">Your phone number</label>
                <input type="text" class="form-control" name="phone" value="${formBeanKey.phone}" readonly id="phone">
                <a class="mt-3" href="/case">Logout</a>
                <a class="mt-2" href="/case">Go to Main page</a>
                <p class="mt-2 text-secondary">If you experience any issues, please contact our <a href="/case/usersupport">user support team.</a></p>
            </form>
        </article>
    </div>
</main>

<jsp:include page="../include/footer.jsp" />