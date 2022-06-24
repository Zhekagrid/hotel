<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 01.06.2022
  Time: 15:25
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


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rating.css">

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>


<c:if test="${sessionScope.bookingSuccess==false}">
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">

        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>



    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
            <use xlink:href="#exclamation-triangle-fill"/>
        </svg>
        <strong>Holy guacamole!</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>



    ${sessionScope.remove("bookingSuccess")}
</c:if>
<form method="post">

    <div class="row   text-center bg-light">
        <div id="carouselExampleControls${requestScope.apartment.id}" class="col-md-4 col-6 carousel slide"
             data-bs-ride="carousel">
            <c:set var="activeItem" value="false"/>
            <c:set var="index" value="0"/>


            <div class="carousel-indicators">
                <c:forEach items="${requestScope.apartment.images}" var="apartmentImage">


                    <c:choose>
                        <c:when test="${index eq 0}">
                            <button type="button" data-bs-target="#carouselExampleControls${requestScope.apartment.id}"
                                    data-bs-slide-to="${index}" class="active" aria-current="true"></button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" data-bs-target="#carouselExampleControls${requestScope.apartment.id}"
                                    data-bs-slide-to="${index}"></button>

                        </c:otherwise>
                    </c:choose>
                    <c:set var="index" value="${index+1}" scope="page"/>

                </c:forEach>
            </div>

            <c:choose>
                <c:when test="${fn:length(requestScope.apartment.images) gt 0}">
                    <div class="carousel-inner">

                        <c:forEach items="${requestScope.apartment.images}" var="apartmentImage">


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
                                data-bs-target="#carouselExampleControls${requestScope.apartment.id}"
                                data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button"
                                data-bs-target="#carouselExampleControls${requestScope.apartment.id}"
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
            <h5 class="title"><fmt:message
                    key="label.apartment.apartmentNumber"/>: ${requestScope.apartment.apartmentNumber}.
                <fmt:message key="label.apartment.class"/>: ${requestScope.apartment.apartmentType.toString()}.</h5>
            <h5 class="title"><fmt:message key="label.apartment.peopleCount"/>: ${requestScope.apartment.peopleCount}.
                <fmt:message key="label.apartment.pricePerDay"/>: ${requestScope.apartment.dayPrice}.</h5>

            <div class="d-flex justify-content-start">

                <p class="text">${requestScope.apartment.description}</p>
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

        </div>


    </div>


    <div class="row">

        <div class="col-12">
            <div class="card">
                <div class="card-header bg-info  text-center">
                    <h4><b class="text-white"><fmt:message key="label.datePicker"/></b></h4>
                </div>
                <div class="card-body">
                    <form action="" method="post">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label><fmt:message key="label.order.FromDate"/></label>
                                    <input type="text" name="fromDate" class="datepicker1 form-control"
                                           autocomplete="off">
                                    <label><fmt:message key="label.order.ToDate"/></label>
                                    <input type="text" name="toDate" class="datepicker1 form-control"
                                           autocomplete="off">

                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="selectedApartmentId" value="${requestScope.apartment.id}">

                        <div class="row">
                            <div class="col-md-12 text-center">
                                <button class="btn btn-success btn-sm" type="submit"
                                        formaction="${pageContext.request.contextPath}/controller?command=book_apartment">
                                    <fmt:message
                                            key="label.book"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    var disableDates = ${requestScope.bookedDates}
        $('.datepicker1').datepicker({
            format: 'dd/mm/yyyy',
            startDate: new Date(),
            endDate: new Date(new Date().setDate(new Date().getDate() + 30)),

            datesDisabled: disableDates
        });

</script>
<script type="text/javascript">
    window.setTimeout(function () {
        $(".alert").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 5000);
</script>
<jsp:include page="footer.jsp"/>


</body>
</html>
