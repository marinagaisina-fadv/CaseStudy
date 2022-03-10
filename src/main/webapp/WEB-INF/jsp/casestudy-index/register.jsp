<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container">
        <h2 class="display-4 text-darkblue text-center">New users registration</h2>
        <article id="article-register" class="m-auto col col-xl-7 col-md-10 shadow-lg rounded15px p-5 mt-4">
            <form method="GET" action="/registerSubmit" name="register-form" class="row">
                <input type="hidden" name="id" value="${formBeanKey.id}">

                <input type="text" class="form-control" name="email" value="${formBeanKey.email}" placeholder="Enter your email in format abc@abc.com">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('email')}">
                        Please enter a valid email address. Check the details below.
                    </c:if>
                </div>
                <input type="text" class="mt-3 form-control" name="firstName" value="${formBeanKey.firstName}" placeholder="Enter your first name">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('firstName')}">
                        Please enter a valid first name. Check the details below.
                    </c:if>
                </div>
                <input type="text" class="mt-3 form-control" name="lastName" value="${formBeanKey.lastName}" placeholder="Enter your last name">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('lastName')}">
                        Please enter a valid last name. Check the details below.
                    </c:if>
                </div>
                <input type="password" class="mt-3 form-control" name="password" value="${formBeanKey.password}" placeholder="Enter your password" aria-describedby="validationPassRegister">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('password')}">
                        Please check password and/or confirmed password fields. Check the details below. You&nbspentered:&nbsp"${formBeanKey.map.get("password").getRejectedValue()}"
                    </c:if>
                </div>
                <input type="password" class="mt-3 form-control" name="confirmPassword" value="${formBeanKey.confirmPassword}" placeholder="Confirm your password">
                <div class="d-flex p-0 flex-column">
                <input type="text" class="mt-3 form-control" name="username" placeholder="Username" readonly>

                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('username')}">
                        Please, generate your username.
                    </c:if>
                </div>
                <button class="btn bg-info bg-gradient btn-lg btn-block" type="button" onclick="generateUsername()">Click for generate your username</button>

                </div>
                <input type="text" class="mt-3 form-control" name="phone" value="${formBeanKey.phone}" placeholder="Enter your cell-phone number">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('phone')}">
                        Please enter a valid cell-phone number Check the details below.
                    </c:if>
                </div>
                <button class="mt-3 btn btn-dark btn-lg btn-block" type="submit">Register</button>
<%--                <button class="w-50 m-auto mt-3 btn btn-secondary btn-lg" type="reset">Reset form values to default</button>--%>
                <div class="mt-3 text-secondary">
                    <c:forEach items="${formBeanKey.map.keySet()}" var="key">
                        <i class="fas fa-circle me-2"></i>Error in '${key}'. ${formBeanKey.getMap().get(key).getDefaultMessage()}<br>
                    </c:forEach>
                </div>
            </form>
        </article>
        <div class="w-75 m-auto mt-4 fs-5 text-darkblue">
            <p><i class="far fa-circle me-2"></i>Make sure you were given a permission  <span class="text-bold">for accessing Inventory Management System</span>.<br>
                Check your email for an email from support@ims.com. Your permission's details must be described in the email under topic "IMA Permission".<br>
            <p><i class="far fa-circle me-2"></i>In order to get a permission, please contact to our <a href="/usersupport">user support team</a>.<br>
            You will need to attach your manager's approval with your request for getting a permission.</p>
            <a class="mt-5 text-muted" href="/case">Go to Main page</a>
        </div>

    </div>
</main>

<jsp:include page="../include/footer.jsp" />