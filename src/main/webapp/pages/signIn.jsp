<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 26.04.2022
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hrydziushka.finalproject.util.validator.ValidatorRegex" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>com.hrydziushka.finalproject.main.Main</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="container">

    <form class="row justify-content-center" method="post" action="${pageContext.request.contextPath}/controller?command=sign_in">
        <p class="text-center fs-2"><fmt:message key="signIn.header"/></p>
        <div class="mb-3 col-12 col-md-4">
            <label for="formGroupExampleInput" class="form-label"><fmt:message key="signIn.login"/></label>
            <input type="text" class="form-control" name="login" id="formGroupExampleInput" placeholder="Enter login" autocomplete="off" title="<fmt:message key="message.incorrect.login"/>" pattern="${ValidatorRegex.LOGIN_REGEX}" required>
        </div>

        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputPassword1" class="form-label"><fmt:message key="signIn.password"/></label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1"  title="<fmt:message key="message.incorrect.password"/>" pattern="${ValidatorRegex.PASSWORD_REGEX}" required>
        </div>

        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">

            <c:if test="${sessionScope.incorrectLoginInformation != null}">
                <div class="text-danger">
                    <fmt:message key="${sessionScope.incorrectLoginInformation}"/>
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary"><fmt:message key="signIn.button"/></button>
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
