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
<c:redirect url="pages/main.jsp" />

</body>
</html>