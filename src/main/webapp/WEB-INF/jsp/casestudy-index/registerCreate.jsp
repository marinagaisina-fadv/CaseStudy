<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container text-darkblue">
        <h2 class="display-7 text-darkblue text-center">Congratulations, <span class="text-bold">${formBeanKey.firstName}!</span></h2>
        <article class="m-auto col col-xl-7 col-md-10 shadow-lg rounded15px p-5 mt-4">
            <p>New user with username <span class="text-bold">"${formBeanKey.username}"</span> was created.</p>
            <p>On your email "${formBeanKey.email}" you will receive a welcome letter from <span class="text-bold">support@ims.com</span> with details.</p>
            <div class="d-flex align-content-center justify-content-end">
                <p class="m-0 align-self-center">Now you can Login in the </p><a class="btn bg-info bg-gradient btn-lg btn-block" href="/case">Main Page</a>
            </div>
            <p class="mt-4 text-secondary">If you experience any issues, please contact our <a href="/usersupport">user support team.</a></p>
        </article>
    </div>
</main>

<jsp:include page="../include/footer.jsp" />