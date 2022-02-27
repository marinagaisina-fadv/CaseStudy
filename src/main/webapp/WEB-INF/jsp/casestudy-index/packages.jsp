<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<%--<c:if test="${searchPa}">--%>
<%--    Please enter a valid last name. Check the details below.--%>
<%--</c:if>--%>

<main class="marginForFooter">
    <form method="GET" action="/case/packages">
        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchParcelsByCustomerNameLike" placeholder="Get list of parcels where customer name LIKE...Type % to see all parcels"
            value="">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('customers-article')" href="#">Open/close</a>
        </div>
    </form>
    <article id="customers-article" class="m-auto col col-xl-10 col-md-12 shadow-lg rounded15px p-2 mt-4">
        <table class="table table-sm table-hover">
            <thead>
            <tr class="text-center">
                <th class="col col-1" scope="col">&numero;</th>
                <th class="col col-2 text-start" scope="col">Pallet Barcode</th>
                <th class="col col-1" scope="col">Package id</th>
                <th class="col col-1" scope="col">Weight</th>
                <th class="col col-1" scope="col">Dimensions</th>
                <th class="col col-2 text-start" scope="col">Customer</th>
                <th class="col col-1 text-secondary" scope="col">Items details:</th>
                <th class="col col-span-3" scope="col">
                    <div class="d-flex text-start text-secondary">
                        <span class="col col-3">Id</span>
                        <span class="col col-6">Name</span>
                        <span class="col col-3" data-bs-toggle="tooltip" data-bs-placement="top" title="Item's quantity in the package">Quantity</span>
                    </div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:set var="rowCount" value="0" scope="page" />
            <c:forEach items="${parcelListKey}" var="parcel">
                <tr class="text-center">
                    <c:set var="rowCount" value="${rowCount + 1}" scope="page"/>
                    <td class="col col-1">${rowCount}</td>
                    <td class="col col-2 text-start"><a href="#" data-bs-placement="top" title="The pallet's barcode that contains the package">${parcel.pallet.barcode}</a></td>
                    <td class="col col-1" data-bs-placement="top" title="The package's Id">${parcel.id}</td>
                    <td class="col col-1" data-bs-toggle="tooltip" data-bs-placement="top" title="The package's weight">${parcel.weight}</td>
                    <td class="col col-1" data-bs-toggle="tooltip" data-bs-placement="top" title="The package's dimensions">${parcel.dimensions}</td>
                    <td class="col col-2 text-start">
                        <a class="position-relative" href="#" onmouseout="customerHoverHandler('customer${parcel.customer.id}')" onmouseover="customerHoverHandler('customer${parcel.customer.id}')">
                            ${parcel.customer.fullName}
                            <div id="customer${parcel.customer.id}" class="d-none position-absolute"><img class="w-50" src="/pub/img/logo.gif" alt="customer.id = ${parcel.customer.id}"></div>
                        </a>
                    </td>
                    <td class="col col-1 text-start">
                        <a href="#" onclick="plusSignClickHandler(this, 'itemsList${rowCount}')">
                            <div>
                                <i class="fa-solid fa-circle-plus"></i>
                                <span>items</span>
                            </div>
                        </a>
                    </td>
                    <td class="text-start col col-3">
                        <div id="itemsList${rowCount}" class="d-none">
                            <c:forEach items="${parcel.items}" var="item">
                                <div class="d-flex justify-content-around">
                                    <span class="col col-3" data-bs-toggle="tooltip" data-bs-placement="top" title="Item's Id in the database">${item.id}</span>
                                    <a href="#" class="col col-6">${item.name}</a>
                                    <span class="col col-3" data-bs-toggle="tooltip" data-bs-placement="top" title="Item's quantity in the package">${item.quantityToPackage}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>

    <form class="mt-5" method="GET" action="/case/packages">
        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchParcelsByItemNameLike" placeholder="Get list of parcels that contain items name LIKE...Type % to see all items">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('items-article')" href="#">Open/close</a>
        </div>
    </form>
    <article id="items-article" class="d-none m-auto col col-xl-10 col-md-12 shadow-lg rounded15px p-5 mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Description</th>
                <th scope="col">Description</th>
                <th scope="col">Description</th>
                <img>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parcelsByItemNameListKey}" var="parcel">
                <tr>
                    <th scope="row">${parcel.id}</th>
                    <td>${parcel.weight}</td>
                    <td>${parcel.dimensions}</td>
                    <td>${parcel.customer.id}</td>
                    <td>${parcel.customer.fullName}</td>
                    <td>${parcel.pallet.id}</td>
                    <td>
                        <c:forEach items="${parcel.items}" var="item">
                            <table class="table table-sm table-success table-striped table-bordered">
                                <tr>
                                    <th>${item.name} (item id =${item.id})</th>
                                </tr>
                            </table>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </article>
</main>

<jsp:include page="../include/footer.jsp" />