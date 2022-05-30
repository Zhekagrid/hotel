<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img  src="${pageContext.request.contextPath}/img/hotel_logo.png">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item ">

<%--                    todo change because now it is go to apartments page--%>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_apartments_page">
                        <fmt:message key="header.home"/></a>
                </li>

                <c:if test="${sessionScope.role=='ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/controller?command=go_to_users_page">
                            <fmt:message key="header.users"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role=='USER'}">


                </c:if>


            </ul>
            <div class="d-flex">

                <c:if test="${sessionScope.role=='GUEST'}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_sign_in_page"> <fmt:message key="header.signIn"/></a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page"> <fmt:message key="header.signUp"/></a>
                </c:if>

                <c:if test="${sessionScope.role!='GUEST'}">

                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">
                                <img  src="${pageContext.request.contextPath}/img/user.png" >
                                <fmt:message key="header.profile"/>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Action</a></li>
                                <li><a class="dropdown-item" href="#">Another action</a></li>

                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=sign_out"> <fmt:message key="header.signOut"/></a>
                                </li>
                            </ul>
                        </li>

                    </ul>


                </c:if>
            </div>
        </div>
    </div>
</nav>


<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>



</body>
</html>