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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="com.hrydziushka.finalproject.util.validator.ValidatorRegex" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">

    <form class="row justify-content-center" method="post"
          action="${pageContext.request.contextPath}/controller?command=sign_up">
        <p class="text-center fs-2"><fmt:message key="signUp.header"/></p>
        <div class="mb-3 col-12 col-md-4">
            <label for="formGroupExampleInput" class="form-label"><fmt:message key="signUp.login"/></label>
            <input type="text" class="form-control" name="login" id="formGroupExampleInput"
                   placeholder="Example input placeholder" value="${requestScope.signUpFormData.login[0]}"autocomplete="off" title="<fmt:message key="message.incorrect.login"/>"  required>
<%--            pattern="${ValidatorRegex.LOGIN_REGEX}"--%>
            <c:if test="${requestScope.signUpFormData.login[1] != null}">
                <div class="text-danger">
                    <fmt:message key="${requestScope.signUpFormData.login[1]}"/>
                </div>
            </c:if>
        </div>

        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputEmail1" class="form-label"><fmt:message key="signUp.email"/></label>
            <input type="text" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp"
                value="${sessionScope.signUpFormData.email[0]}"   >
            <c:if test="${sessionScope.signUpFormData.email[1] != null}">
                <div class="text-danger">
                    <fmt:message key="${sessionScope.signUpFormData.email[1]}"/>
                </div>
            </c:if>


        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputPassword1" class="form-label"><fmt:message key="signUp.password"/></label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1"
                 value="${sessionScope.signUpFormData.password[0]}"  >
            <c:if test="${sessionScope.signUpFormData.password[1] != null}">
                <div class="text-danger">
                    <fmt:message key="${sessionScope.signUpFormData.password[1]}"/>
                </div>
            </c:if>
        </div>
        <div class="w-100"></div>
        <div class="mb-3 col-12 col-md-4">
            <label for="exampleInputPassword2" class="form-label"><fmt:message key="signUp.repeatPassword"/></label>
            <input type="password" class="form-control" name="repeatPassword" id="exampleInputPassword2"value="${sessionScope.signUpFormData.repeatPassword[0]}">
            <c:if test="${sessionScope.signUpFormData.repeatPassword[1] != null}" >
                <div class="text-danger">
                    <fmt:message key="${sessionScope.signUpFormData.repeatPassword[1]}"/>
                </div>
            </c:if>
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
