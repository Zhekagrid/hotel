<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 25.05.2022
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ex.css">

</head>
<body>
<jsp:include page="header.jsp"/>
<c:forEach items="${requestScope.apartments}" var="apartment">

    <div class="card-header">
        Apartments
    </div>

    <div class="row  text-center">

        <div id="carouselExampleControls${apartment.id}" class="col-sm-6 carousel slide" data-bs-ride="carousel">
            <c:set var="activeItem" value="false"/>
            <c:set var="index" value="0" />


            <div class="carousel-indicators">
                <c:forEach items="${apartment.images}" var="apartmentImage">


                    <c:choose>

                        <c:when test="${index eq 0}">

                            <button type="button" data-bs-target="#carouselExampleControls${apartment.id}"
                                    data-bs-slide-to="${index}" class="active" aria-current="true"></button>
                        </c:when>

                        <c:otherwise>
                            <button type="button" data-bs-target="#carouselExampleControls${apartment.id}"
                                    data-bs-slide-to="${index}" ></button>

                        </c:otherwise>
                    </c:choose>
                    <c:set var="index" value="${index+1}" scope="page"/>

                </c:forEach>
            </div>

            <c:choose>
                <c:when test="${fn:length(apartment.images) gt 0}">
                    <div class="carousel-inner">

                        <c:forEach items="${apartment.images}" var="apartmentImage">


                            <c:choose>

                                <c:when test="${activeItem eq false}">
                                    <div class="carousel-item active box" >
                                        <img class="d-block " src="${apartmentImage.encodedImage}"width="100%">
                                    </div>
                                    <c:set var="activeItem" value="true"/>
                                </c:when>

                                <c:otherwise>
                                    <div class="carousel-item box" >
                                        <img class="d-block " src="${apartmentImage.encodedImage}"  height="100%" width="100%">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <button class="carousel-control-prev" type="button"
                                data-bs-target="#carouselExampleControls${apartment.id}"
                                data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleControls${apartment.id}"
                                data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>

                    </div>
                </c:when>
                <c:otherwise>
                    <div class="carousel-inner">

                        <div class="carousel-item active box">
                            <img class="d-block w-auto"
                                 src="${pageContext.request.contextPath}/img/img.png">
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
<%--        <div id="carouselExampleIndicators" class="col-sm-6  carousel slide" data-bs-ride="carousel">--%>
<%--            <div class="carousel-indicators">--%>
<%--                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>--%>
<%--                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>--%>
<%--                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>--%>
<%--            </div>--%>
<%--            <div class="carousel-inner">--%>
<%--                <div class="carousel-item active">--%>
<%--                    <img src="..." class="d-block w-100" alt="...">--%>
<%--                </div>--%>
<%--                <div class="carousel-item">--%>
<%--                    <img src="..." class="d-block w-100" alt="...">--%>
<%--                </div>--%>
<%--                <div class="carousel-item">--%>
<%--                    <img src="..." class="d-block w-100" alt="...">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">--%>
<%--                <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--                <span class="visually-hidden">Previous</span>--%>
<%--            </button>--%>
<%--            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">--%>
<%--                <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--                <span class="visually-hidden">Next</span>--%>
<%--            </button>--%>
<%--        </div>--%>
        <div class="col-sm-6">
            <h5 class="title">Заголовок карточки</h5>
            <p class="text">Небольшой пример текста, который должен основываться на названии карточки и составлять
                основную часть содержимого карты.</p>
            <a href="#" class="btn btn-primary">Перейти куда-нибудь</a>
        </div>
    </div>


    <%--    TABLE BAD--%>
    <%--    <table class="table table-hover table-fixed">--%>
    <%--        <thead>--%>
    <%--        <tr>--%>
    <%--            <th scope="col"><fmt:message key="label.apartment.apartmentNumber"/></th>--%>
    <%--            <th style="width: 30%" scope="col"><fmt:message key="label.apartment.images"/></th>--%>
    <%--            <th scope="col"><fmt:message key="label.apartment.class"/></th>--%>
    <%--            <th scope="col"><fmt:message key="label.apartment.peopleCount"/></th>--%>
    <%--            <th scope="col"><fmt:message key="label.apartment.description"/></th>--%>
    <%--            <th scope="col"><fmt:message key="label.apartment.pricePerDay"/></th>--%>
    <%--        </tr>--%>
    <%--        </thead>--%>
    <%--        <tbody>--%>
    <%--        <tr>--%>
    <%--            <th scope="row">${apartment.apartmentNumber}</th>--%>
    <%--            <td>--%>
    <%--                <div id="carouselExampleControls${apartment.id}" class="carousel slide" data-bs-ride="carousel">--%>
    <%--                    <c:set var="activeItem" value="false"/>--%>
    <%--                    <c:choose>--%>
    <%--                        <c:when test="${fn:length(apartment.images) gt 0}">--%>
    <%--                            <div class="carousel-inner">--%>

    <%--                                <c:forEach items="${apartment.images}" var="apartmentImage">--%>


    <%--                                    <c:choose>--%>

    <%--                                        <c:when test="${activeItem eq false}">--%>
    <%--                                            <div class="carousel-item active box">--%>
    <%--                                                <img class="d-block w-100" src="${apartmentImage.encodedImage}">--%>
    <%--                                            </div>--%>
    <%--                                            <c:set var="activeItem" value="true"/>--%>
    <%--                                        </c:when>--%>

    <%--                                        <c:otherwise>--%>
    <%--                                            <div class="carousel-item box">--%>
    <%--                                                <img class="d-block w-100" src="${apartmentImage.encodedImage}">--%>
    <%--                                            </div>--%>
    <%--                                        </c:otherwise>--%>
    <%--                                    </c:choose>--%>
    <%--                                </c:forEach>--%>
    <%--                                <button class="carousel-control-prev" type="button"--%>
    <%--                                        data-bs-target="#carouselExampleControls${apartment.id}"--%>
    <%--                                        data-bs-slide="prev">--%>
    <%--                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
    <%--                                    <span class="visually-hidden">Previous</span>--%>
    <%--                                </button>--%>
    <%--                                <button class="carousel-control-next" type="button"--%>
    <%--                                        data-bs-target="#carouselExampleControls${apartment.id}"--%>
    <%--                                        data-bs-slide="next">--%>
    <%--                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
    <%--                                    <span class="visually-hidden">Next</span>--%>
    <%--                                </button>--%>

    <%--                            </div>--%>
    <%--                        </c:when>--%>
    <%--                        <c:otherwise>--%>
    <%--                            <div class="carousel-inner">--%>

    <%--                                <div class="carousel-item active box">--%>
    <%--                                    <img class="d-block w-100"--%>
    <%--                                         src="${pageContext.request.contextPath}/img/img.png">--%>
    <%--                                </div>--%>
    <%--                            </div>--%>
    <%--                        </c:otherwise>--%>
    <%--                    </c:choose>--%>

    <%--                </div>--%>
    <%--            </td>--%>
    <%--            <td>${apartment.apartmentType}</td>--%>
    <%--            <td>${apartment.peopleCount}</td>--%>
    <%--            <td>${apartment.description}</td>--%>
    <%--            <td>${apartment.dayPrice}</td>--%>
    <%--        </tr>--%>

    <%--    </table>--%>
</c:forEach>


<nav aria-label="navigation for items">
    <ul class="pagination justify-content-center mt-3 mb-4">
        <c:if test="${requestScope.page != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/controller?command=go_to_apartments_page&apartmentsCountPerPage=${requestScope.apartmentsCountPerPage}&page=${requestScope.page-1}">
                    <fmt:message key="pages.prev"/>
                </a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${requestScope.pagesCount}" var="i">
            <c:choose>
                <c:when test="${requestScope.page eq i}">
                    <li class="page-item active">
                        <a class="page-link"> ${i} <span class="sr-only"></span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/controller?command=go_to_apartments_page&apartmentsCountPerPage=${requestScope.apartmentsCountPerPage}&page=${i}">
                                ${i}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${requestScope.page lt requestScope.pagesCount}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/controller?command=go_to_apartments_page&apartmentsCountPerPage=${requestScope.apartmentsCountPerPage}&page=${requestScope.page+1}">
                    <fmt:message key="pages.next"/>
                </a>
            </li>
        </c:if>

        <form method="get" action="${pageContext.request.contextPath}/controller">

            <input type="hidden" name="command" value="go_to_apartments_page"/>
            <div class="form-group col-md-4">

                <label for="records"><fmt:message key="pages.itemsCount"/></label>

                <select id="records" name="apartmentsCountPerPage">
                    <option value="1"${requestScope.apartmentsCountPerPage == 1 ? ' selected' : ''}>1
                    </option>
                    <option value="2" ${requestScope.apartmentsCountPerPage== 2 ? ' selected' : ''}>2
                    </option>
                    <option value="3" ${requestScope.apartmentsCountPerPage == 3 ? ' selected' : ''}>3
                    </option>
                </select>

            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="pages.apply"/></button>

        </form>


    </ul>
</nav>
<%--<div class="container-fluid">--%>
<%--    <div class="row">--%>
<%--        <main class="col m-1 mt-1">--%>


<%--            <c:forEach items="${requestScope.apartments}" var="apartment">--%>
<%--                <form method="post" action="">--%>
<%--                    <table class="table">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th scope="col">#</th>--%>
<%--                            <th scope="col">First</th>--%>
<%--                            <th scope="col">Last</th>--%>
<%--                            <th scope="col">Handle</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <th scope="row">1</th>--%>
<%--                            <td>Mark</td>--%>
<%--                            <td>Otto</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <th scope="row">2</th>--%>
<%--                            <td>Jacob</td>--%>
<%--                            <td>Thornton</td>--%>
<%--                            <td>@fat</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <th scope="row">3</th>--%>
<%--                            <td>Larry</td>--%>
<%--                            <td>the Bird</td>--%>
<%--                            <td>@twitter</td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </form>--%>
<%--            </c:forEach>--%>


<%--            <div class="row">--%>
<%--                <table class="table table-striped">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th scope="col"><fmt:message key="label.apartment.apartmentNumber"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.apartment.class"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.apartment.peopleCount"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.apartment.pricePerDay"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.apartment.description"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.user.status"/></th>--%>
<%--                        <th scope="col"><fmt:message key="label.user.role"/></th>--%>

<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <c:forEach items="${requestScope.users}" var="user">--%>
<%--                        <form method="post"--%>
<%--                              action="${pageContext.request.contextPath}/controller?command=save_user_changes">--%>
<%--                            <input type="hidden" name="selectedUserId" value="${user.id}">--%>
<%--                            <tr>--%>

<%--                                <th scope="row">${user.id}</th>--%>
<%--                                <td>${user.login}</td>--%>
<%--                                <td>${user.email}</td>--%>
<%--                                <td>${user.phoneNumber}</td>--%>
<%--                                <td>${user.balance}</td>--%>

<%--                                <td>--%>
<%--                                    <select name="selectedUserStatus" class="form-select"--%>
<%--                                            aria-label="Default select example">--%>
<%--                                        <option value="BLOCKED" ${user.userStatus == 'BLOCKED' ? ' selected' : ''}>--%>
<%--                                            <fmt:message key="user.status.blocked"/>--%>
<%--                                        </option>--%>
<%--                                        <option value="ACTIVE" ${user.userStatus == 'ACTIVE' ? ' selected' : ''}>--%>
<%--                                            <fmt:message key="user.status.active"/>--%>
<%--                                        </option>--%>

<%--                                    </select>--%>
<%--                                </td>--%>
<%--                                <td>--%>
<%--                                    <select name="selectedUserRole" class="form-select"--%>
<%--                                            aria-label="Default select example">--%>
<%--                                        <option value="USER" ${user.userRole == 'USER' ? ' selected' : ''}>--%>
<%--                                            <fmt:message key="user.role.user"/>--%>
<%--                                        </option>--%>

<%--                                        <option value="ADMIN" ${user.userRole== 'ADMIN' ? ' selected' : ''}>--%>
<%--                                            <fmt:message key="user.role.admin"/>--%>
<%--                                        </option>--%>

<%--                                    </select>--%>
<%--                                </td>--%>
<%--                                <td>--%>
<%--                                        <span><button class="btn btn-outline-primary" type="submit"><fmt:message--%>
<%--                                                key="label.save"/> </button></span>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </form>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--                <nav aria-label="navigation for items">--%>
<%--                    <ul class="pagination justify-content-center mt-3 mb-4">--%>
<%--                        <c:if test="${requestScope.page != 1}">--%>
<%--                            <li class="page-item">--%>
<%--                                <a class="page-link"--%>
<%--                                   href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${requestScope.page-1}">--%>
<%--                                    <fmt:message key="pages.prev"/>--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                        </c:if>--%>
<%--                        <c:forEach begin="1" end="${requestScope.pagesCount}" var="i">--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${requestScope.page eq i}">--%>
<%--                                    <li class="page-item active">--%>
<%--                                        <a class="page-link"> ${i} <span class="sr-only"></span></a>--%>
<%--                                    </li>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <li class="page-item">--%>
<%--                                        <a class="page-link"--%>
<%--                                           href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${i}">--%>
<%--                                                ${i}--%>
<%--                                        </a>--%>
<%--                                    </li>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${requestScope.page lt requestScope.pagesCount}">--%>
<%--                            <li class="page-item">--%>
<%--                                <a class="page-link"--%>
<%--                                   href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${requestScope.page+1}">--%>
<%--                                    <fmt:message key="pages.next"/>--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                        </c:if>--%>

<%--                        <form method="get"       action="${pageContext.request.contextPath}/controller">--%>

<%--                            <input type="hidden" name="command" value="go_to_home_page"/>--%>
<%--                            <div class="form-group col-md-4">--%>

<%--                                <label for="records"><fmt:message key="pages.itemsCount"/></label>--%>

<%--                                <select id="records" name="usersCountPerPage">--%>
<%--                                    <option value="1"${requestScope.usersCountPerPage == 1 ? ' selected' : ''}>1--%>
<%--                                    </option>--%>
<%--                                    <option value="2" ${requestScope.usersCountPerPage == 2 ? ' selected' : ''}>2--%>
<%--                                    </option>--%>
<%--                                    <option value="3" ${requestScope.usersCountPerPage == 3 ? ' selected' : ''}>3--%>
<%--                                    </option>--%>
<%--                                </select>--%>

<%--                            </div>--%>

<%--                            <button type="submit" class="btn btn-primary"><fmt:message key="pages.apply"/></button>--%>

<%--                        </form>--%>


<%--                    </ul>--%>
<%--                </nav>--%>
<%--            </div>--%>

<%--        </main>--%>
<%--    </div>--%>
<%--</div>--%>


<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
