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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rating.css">

</head>
<body>
<jsp:include page="header.jsp"/>
<h1 class="page-header text-center">Название статьи</h1>
<c:forEach items="${requestScope.apartments}" var="apartment">


    <form method="post">
        <input type="hidden" name="selectedApartmentId" value="${apartment.id}">

    <div class="row   text-center bg-light">
            <div id="carouselExampleControls${apartment.id}" class="col-md-4 col-6 carousel slide"
                 data-bs-ride="carousel">
                <c:set var="activeItem" value="false"/>
                <c:set var="index" value="0"/>


                <div class="carousel-indicators">
                    <c:forEach items="${apartment.images}" var="apartmentImage">


                        <c:choose>

                            <c:when test="${index eq 0}">

                                <button type="button" data-bs-target="#carouselExampleControls${apartment.id}"
                                        data-bs-slide-to="${index}" class="active" aria-current="true"></button>
                            </c:when>

                            <c:otherwise>
                                <button type="button" data-bs-target="#carouselExampleControls${apartment.id}"
                                        data-bs-slide-to="${index}"></button>

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
                                        <div class="carousel-item active box">
                                            <img class="d-block " src="${apartmentImage.encodedImage}" width="100%">
                                        </div>
                                        <c:set var="activeItem" value="true"/>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="carousel-item box ">
                                            <img class="d-block " src="${apartmentImage.encodedImage}"
                                                 width="100%">
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
                                <img class="d-block w-100"
                                     src="${pageContext.request.contextPath}/img/img.png">
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>

            <div class="col-md-8 col-6">
                <h3 class="title"><fmt:message key="label.apartment.description"/></h3>
                <h5 class="title"><fmt:message key="label.apartment.apartmentNumber"/>: ${apartment.apartmentNumber}.
                    <fmt:message key="label.apartment.class"/>: ${apartment.apartmentType.toString()}.</h5>
                <h5 class="title"><fmt:message key="label.apartment.peopleCount"/>: ${apartment.peopleCount}.
                    <fmt:message key="label.apartment.pricePerDay"/>: ${apartment.dayPrice}.</h5>

                <div class="d-flex justify-content-start">

                    <p class="text">${apartment.description}</p>
                </div>
                <div class="rating-result d-flex justify-content-start">
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    (5)
                </div>
                <div class="rating-result d-flex justify-content-start">
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    <span></span>
                    <a href="">
                        5
                    </a>
                </div>
                <div class="rating-result d-flex justify-content-start ">
                    <span class="active"></span>
                    <span class="active"></span>
                    <span class="active"></span>
                    <span></span>
                    <span></span>
                    (5)
                </div>
                <div class="rating-result d-flex justify-content-start ">
                    <span class="active"></span>
                    <span class="active"></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    (5)
                </div>
                <div class="rating-result d-flex justify-content-start">
                    <span class="active"></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    (5)
                </div>
                <div class="d-flex justify-content-start">
                    <button type="submit" class="btn btn-primary  "
                            formaction="${pageContext.request.contextPath}/controller?command=go_to_book_apartment_page">
                        <fmt:message key="label.book"/>
                    </button>
                </div>
            </div>


        </div>


    </form>


</c:forEach>

<div class="row">
    <div class="col-sm-6 ">
        <nav aria-label="navigation for items">
            <ul class="pagination justify-content-end  mt-3 mb-4">
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


            </ul>

        </nav>
    </div>
    <div class="col-sm-6 gy-3">
        <form method="get" action="${pageContext.request.contextPath}/controller">

            <input type="hidden" name="command" value="go_to_apartments_page"/>
            <div class="form-group ">

                <label for="records"><fmt:message key="pages.itemsCount"/></label>

                <select id="records" name="apartmentsCountPerPage">
                    <option value="1"${requestScope.apartmentsCountPerPage == 1 ? ' selected' : ''}>1
                    </option>
                    <option value="2" ${requestScope.apartmentsCountPerPage== 2 ? ' selected' : ''}>2
                    </option>
                    <option value="3" ${requestScope.apartmentsCountPerPage == 3 ? ' selected' : ''}>3
                    </option>
                </select>
                <button type="submit" class="btn btn-primary"><fmt:message key="pages.apply"/></button>

            </div>


        </form>
    </div>

</div>

<jsp:include page="footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
