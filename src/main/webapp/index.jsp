<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<fmt:message key="header.signIn" var="header_signIn"/>
<html>
<head>
   <title>JSP - Hello World</title>
</head>
<body>
<jsp:include page="pages/header.jsp"/>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="text" name="password" value=""/>
    <br/>
    ${header_signIn}

    <input type="submit" name="sub" value="push"/>
    <br/>
    ${login_msg}
</form>




</body>
</html>