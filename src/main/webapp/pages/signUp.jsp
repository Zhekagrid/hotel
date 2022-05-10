<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 26.04.2022
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">

    <form class="row justify-content-center" method="post" action="${pageContext.request.contextPath}/controller?command=sign_up">
        <p class="text-center fs-2"><fmt:message key="signUp.header"/></p>
        <div class="mb-3 col-12 col-md-4">
            <label for="formGroupExampleInput" class="form-label"><fmt:message key="signUp.login"/></label>
            <input type="text" class="form-control" name="login" id="formGroupExampleInput" placeholder="Example input placeholder">
        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputEmail1" class="form-label"><fmt:message key="signUp.email"/></label>
            <input type="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputPassword1" class="form-label"><fmt:message key="signUp.password"/></label>
            <input type="password" class="form-control"name="password" id="exampleInputPassword1">
        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputPassword2" class="form-label"><fmt:message key="signUp.repeatPassword"/></label>
            <input type="password" class="form-control" name="repeat_password" id="exampleInputPassword2">
        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <button type="submit" class="btn btn-primary"><fmt:message key="signUp.button"/></button>
        </div>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
