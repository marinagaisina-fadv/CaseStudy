<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<main>
    <form method="GET" action="/case/allitems">
        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchParcels" placeholder="Get list of parcels where customer name LIKE...Type % to see all parcels">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('customers-article')" href="#">Open/close</a>
        </div>
    </form>
    <article id="customers-article" class="m-auto col col-xl-8 col-md-12 shadow-lg rounded15px p-5 mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Parcel id</th>
                <th scope="col">item_id</th>
                <th scope="col">item_name</th>
                <th scope="col">customer_id</th>
                <th scope="col">customer_name</th>
                <th scope="col">pallet_id</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parcelListKey}" var="parcel">
                <tr>
                    <th scope="row">${parcel.id}</th>
                    <td>${parcel.item.id}</td>
                    <td>${parcel.item.name}</td>
                    <td>${parcel.customer.id}</td>
                    <td>${parcel.customer.fullName}</td>
                    <td>${parcel.pallet.id}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>

    <form class="mt-5" method="GET" action="/case/allitems">
        <div class="m-auto col col-xl-5 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchItems" placeholder="Find a list of items by name LIKE...Type % to see all items">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('items-article')" href="#">Open/close</a>
        </div>
    </form>
    <article id="items-article" class="m-auto col col-xl-8 col-md-12 shadow-lg rounded15px p-5 mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Available Quantity</th>
                <img>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemListKey}" var="item">
                <tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.availQuantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>
</main>

<jsp:include page="../include/footer.jsp" />