<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" >

</head>
<body>

<nav class="navbar mb-6 navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <fmt:message key="header.brand"/>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-6 mb-lg-0">
            <li class="nav-item ">
                <a class="nav-link" href="#">
                    <fmt:message key="header.home"/></a>
            </li>

            <c:if test="${sessionScope.role=='ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=go_to_users_page">
                        <fmt:message key="header.users"/></a>
                </li>
            </c:if>
            <c:if test="${sessionScope.role=='USER'}">

            </c:if>
            <c:if test="${sessionScope.role=='GUEST'}">

                <li class="nav-item">
                    <a class="nav-link" href="#"> <fmt:message key="header.signIn"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"> <fmt:message key="header.signUp"/></a>
                </li>

            </c:if>
            <c:if test="${sessionScope.role!='GUEST'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"> <fmt:message key="header.signOut"/></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link " href="#">
                        <img src="<c:url value="/img/user.png"/>"></a>
                </li>
            </c:if>

            <li class="nav-item ">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/controller?command=change_locale&language=en">EN</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/controller?command=change_locale&language=ru">RU</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/controller?command=change_locale&language=de">DE</a>
            </li>


        </ul>

    </div>
</nav>



<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>


</body>
</html>