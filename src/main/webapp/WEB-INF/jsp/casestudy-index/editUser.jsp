<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container">
        <h2 class="display-4 text-darkblue text-center">Edit user's profile</h2>
        <article id="article-register" class="m-auto col col-xl-7 col-md-10 shadow-lg rounded15px p-5 mt-4">
            <form method="GET" action="/case/editUserSubmit" class="row">
                <input type="hidden" name="id" value="${formBeanKey.id}">

                <input type="text" class="form-control" name="locationAddress" value="${formBeanKey.locationAddress}" readonly>
                <div class="text-secondary">
                    You can't change your default location. Your location can be changed by user support team.
                </div>
                <input type="text" class="form-control" name="email" value="${formBeanKey.email}" readonly>
                <div class="text-secondary">
                    You can't change your email. You may be given a new account by user support team.
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
                        Please check password and/or confirmed password fields. Check the details below. You&nbspentered:&nbsp"${formBeanKey.map.get('password').getRejectedValue()}"
                    </c:if>
                </div>
                <input type="password" class="mt-3 form-control" name="confirmPassword" value="${formBeanKey.confirmPassword}" placeholder="Confirm your password">
                <input type="text" class="mt-3 form-control" name="username" value="${formBeanKey.username}" readonly>

                <div class="text-secondary">
                    You can't change your username. You may be given a new account by user support team.
                </div>

                <input type="text" class="mt-3 form-control" name="phone" value="${formBeanKey.phone}" placeholder="Enter your cell-phone number">
                <div class="text-danger">
                    <c:if test="${formBeanKey.map.containsKey('phone')}">
                        Please enter a valid cell-phone number Check the details below.
                    </c:if>
                </div>
                <button class="mt-3 btn btn-dark btn-lg btn-block" type="submit">Submit Edit</button>
                <%--                <button class="w-50 m-auto mt-3 btn btn-secondary btn-lg" type="reset">Reset form values to default</button>--%>
                <div class="mt-3 text-secondary">
                    <c:forEach items="${formBeanKey.map.keySet()}" var="key">
                        <i class="fas fa-circle me-2"></i>Error in '${key}'. ${formBeanKey.getMap().get(key).getDefaultMessage()}<br>
                    </c:forEach>
                </div>
                <a class="mt-5 text-muted" href="/case">Go to Main page (without saving the changes)</a>
            </form>
        </article>
    </div>
</main>

<jsp:include page="../include/footer.jsp" />