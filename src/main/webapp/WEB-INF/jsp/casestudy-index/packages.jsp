<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<%--<c:if test="${searchPa}">--%>
<%--    Please enter a valid last name. Check the details below.--%>
<%--</c:if>--%>

<main class="marginForFooter">
    <form method="GET" action="/packages">
        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchParcelsByCustomerNameLike" placeholder="Get list of packages where customer name LIKE, ignore case...Type % to see all parcels"
            value="${searchParcelsByCustomerNameLike}">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('customers-article')" href="#">Open/close</a>
        </div>
        <article id="customers-article" class="m-auto col col-xl-10 col-md-12 shadow-lg rounded15px p-2 mt-4">
            <table class="table table-sm table-hover">
            <thead>
            <tr class="text-center">
                <th class="col col-1" scope="col">&numero;</th>
                <th class="col col-1" scope="col">Package id</th>
                <th class="col col-2 text-start" scope="col">Pallet Barcode</th>
                <%--<th class="col col-1" scope="col">Weight</th>
                <th class="col col-1" scope="col">Dimensions</th>--%>
                <th class="col col-2 text-start" scope="col">Customer</th>
                <th class="col col-1" scope="col"></th>
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
                <tr class="text-start">
                    <c:set var="rowCount" value="${rowCount + 1}" scope="page"/>
                    <td class="col col-1 text-center">${rowCount}</td>
                    <td class="col col-1" data-bs-placement="top" title="The package's Id"><a class="w-100 btn bg-lightblue" href="/editPackage?id=${parcel.id}">${parcel.id}</a></td>
                    <td class="col col-1" data-bs-placement="top" title="The pallet's barcode that contains the package">
                        <c:if test="${parcel.pallet.id != null}">
                            <a class="w-100 btn text-start" href="/editPallet?id=${parcel.pallet.id}">${parcel.pallet.barcode}</a>
                        </c:if>
                        <c:if test="${parcel.pallet.id == null}">
                            <a class="w-100 btn bg-warning bg-gradient" href="/addPackageToPallet?id=${parcel.pallet.id}">Add to pallet</a>
                        </c:if>
                    </td>
                    <%--
                    <td class="col col-2 position-relative" onmouseout="customerHoverHandler('customer${parcel.customer.id}')" onmouseover="customerHoverHandler('customer${parcel.customer.id}')">
                        <a class="w-100 text-start" href="#">
                                ${parcel.customer.fullName}
                            <div id="customer${parcel.customer.id}" class="d-none position-absolute"><img class="w-50" src="/pub/img/logo.gif" alt="customer.id = ${parcel.customer.id}"></div>
                        </a>
                    </td>--%>
                    <td class="col col-2" data-bs-placement="top" title="The customer's name"><a class="w-100 btn text-start" href="/customerDetails?id=${parcel.customer.id}">${parcel.customer.fullName}</a></td>
                    <td class="col col-1">
                        <c:if test="${parcel.pallet.id == null}">
                            <a class="btn btn-info bg-gradient" href="/items?id=${parcel.id}">Add Items</a>
                        </c:if>
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
                                    <a href="/itemDetails?id=${item.id}" class="col col-6" style=":hover {cursor: pointer}; :focus {cursor: pointer};"
                                       onmouseout="itemHoverHandler('item${item.id}')" onmouseover="itemHoverHandler('item${item.id}')">
                                            ${item.name}

                                    </a>
                                    <div id="item${item.id}" class="d-none position-absolute end-0"><img class="w-25" src="${item.imageUrl}" alt="item.id =${item.id}"></div>
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


        <div class="mt-5 m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchParcelsByItemNameLike" placeholder="Get list of packages that contain items with name LIKE, ignore Case...Type % to see all items"
                   value="${searchParcelsByItemNameLike}">
            <button class="btn btn-dark" type="submit">Search</button>
            <a class="ms-2 btn btn-secondary" onclick="formOpenClose('items-article')" href="#">Open/close</a>
        </div>

        <article id="items-article" class="m-auto col col-xl-10 col-md-12 shadow-lg rounded15px p-2 mt-4">
            <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">weight</th>
                <th scope="col">dimensions</th>
                <th scope="col">customer's id</th>
                <th scope="col">customer's name</th>
                <th scope="col">pallet.id</th>
                <th scope="col">list of items</th>
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
    </form>

</main>

<jsp:include page="../include/footer.jsp" />

<%-- MODAL WINDOW!!!
<!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            Launch static backdrop modal
        </button>

        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Add Items to Package id=</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
                            <input type="text" class="form-control" name="searchItemsByItemName" placeholder="Get list of items that contain name, ignore case...Type % to see all items"
                                   value="${searchItemsByItemName}">
                            <button class="btn btn-dark" type="submit">Search</button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Understood</button>
                    </div>
                </div>
            </div>
        </div>
--%>