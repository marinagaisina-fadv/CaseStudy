<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<jsp:include page="../include/header.jsp" />

<main>
    <div class="marginForFooter container">
        <h2 class="display-4 text-darkblue text-center">Contact to user support team</h2>
        <article class="m-auto col col-xl-7 col-md-10 shadow-lg rounded15px p-5 mt-3">
            <form action="#" name="usersupportForm" class="row needs-validation" novalidate>
                <input type="email" class="form-control" name="email" placeholder="Enter your email in format abc@abc.com" required aria-describedby="validationEmailSupport">
                <!--                        <div class="valid-feedback">-->
                <!--                            Passed!-->
                <!--                        </div>-->
                <div id="validationEmailSupport" class="invalid-feedback">
                    Please enter a valid email address.
                </div>
                <textarea class="mt-3 form-control" name="reason" placeholder="Please describe a reason for requesting a contact..." rows="3" required aria-describedby="validationReason"></textarea>
                <!--                        <div class="valid-feedback">-->
                <!--                            Passed!-->
                <!--                        </div>-->
                <div id="validationReason" class="invalid-feedback">
                    This field shouldn't be empty.
                </div>
                <div class="mt-3">
                    <label for="formFile1" class="form-label">Attach file:</label>
                    <input class="form-control" type="file" id="formFile1">
                    <label for="formFile2" class="mt-2 form-label">Attach file:</label>
                    <input class="form-control" type="file" id="formFile2">
                    <label for="formFile3" class="mt-2 form-label">Attach file:</label>
                    <input class="form-control" type="file" id="formFile3">
                </div>
                <div class="mt-3 w-25 ms-auto">
                    <button class="w-100 btn btn-dark btn-lg" type="submit">SEND</button>
                </div>
                <a class="mt-5 text-muted" href="/case">Go to Main page</a>
            </form>
        </article>
    </div>
</main>

<jsp:include page="../include/footer.jsp" />