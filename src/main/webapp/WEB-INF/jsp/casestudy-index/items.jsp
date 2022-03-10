<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />

<%--<c:if test="${searchPa}">--%>
<%--    Please enter a valid last name. Check the details below.--%>
<%--</c:if>--%>

<main class="marginForFooter">
    <form method="GET" action="/items">
        <div class="m-auto col col-xl-8 col-md-10 d-flex shadow-lg rounded15px">
            <input type="text" class="form-control" name="searchItemsByItemName" placeholder="Get list of items that contain name, ignore case"
                   value="${searchItemsByItemName}">
            <button class="btn btn-dark" type="submit">Search</button>
        </div>
        <article id="customers-article" class="m-auto col col-xl-8 col-md-10 shadow-lg rounded15px p-2 mt-4">
            <table class="table table-sm table-hover">
                <thead>
                    <tr class="text-center">
                        <th class="col col-1" scope="col">&numero;</th>
                        <th class="col col-1 text-start" scope="col">Item Id</th>
                        <th class="col col-1" scope="col"></th>
                        <th class="col col-2 text-start" scope="col">Item name</th>
                        <th class="col col-3 text-start text-wrap" scope="col">Description</th>
                        <th class="col col-1 text-start" scope="col">Available Quantity</th>
                        <th class="col col-1" scope="col"></th>
                    </tr>
                </thead>

                <tbody>
                <c:set var="rowCount" value="0" scope="page" />
                <c:forEach items="${itemListKey}" var="item">
                    <tr class="text-start">
                        <c:set var="rowCount" value="${rowCount + 1}" scope="page"/>
                        <td class="col col-1 text-center">${rowCount}</td>
                        <td class="col col-1" data-bs-placement="top" title="The item's id">${item.id}</td>
                        <td class="col col-1"><a href="/itemDetails?id=${item.id}"><img class="w-100" src="${item.imageUrl}"></a></td>
                        <td class="col col-2" data-bs-placement="top" title="The item's name">${item.name}</td>
                        <td class="col col-3" data-bs-placement="top" title="The item's description">${item.description}</td>
                        <td class="col col-1" data-bs-placement="top" title="The available quantity">${item.availQuantity}</td>
                        <td class="col col-3"><a class="w-100 btn bg-lightblue" href="/addItemToPackage">Add to package</a></td>
                            <%--
                            <td class="col col-2 position-relative" onmouseout="customerHoverHandler('customer${parcel.customer.id}')" onmouseover="customerHoverHandler('customer${parcel.customer.id}')">
                                <a class="w-100 text-start" href="#">
                                        ${parcel.customer.fullName}
                                    <div id="customer${parcel.customer.id}" class="d-none position-absolute"><img class="w-50" src="/pub/img/logo.gif" alt="customer.id = ${parcel.customer.id}"></div>
                                </a>
                            </td>
                        <td class="col col-2" data-bs-placement="top" title="The customer's name"><a class="w-100 btn text-start" href="/customerDetails?id=${parcel.customer.id}">${parcel.customer.fullName}</a></td>
                        <td class="col col-1">
                            <c:if test="${parcel.pallet.id == null}">
                                <a class="btn btn-info bg-gradient" href="/addItemsToPackage?id=${parcel.id}">Add Items</a>
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
                        </td>--%>
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
                            <input type="text" class="form-control" name="searchItemsByItemName" placeholder="Get list of items that contain name, ignore case..."
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