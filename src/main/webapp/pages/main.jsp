<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 29.03.2022
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>com.hrydziushka.finalproject.main.Main</title>
    ${sessionScope.USER}
</head>
<body>
<jsp:include page="header.jsp"/>
USER =${user}
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="log_in"/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="text" name="password" value=""/>
    <br/>
    ${header_signIn}

    <input type="submit" name="sub" value="push"/>
    <br/>
    ${login_msg}
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
