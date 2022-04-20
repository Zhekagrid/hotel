<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 30.03.2022
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
Request from: ${pageContext.errorData.requestURI} is failed
Servlet name: ${pageContext.errorData.servletName}<br/>
Status code: ${pageContext.errorData.statusCode}<br/>
Exception: ${pageContext.exception}<br/>
Message: ${pageContext.exception.message}<br/>


</body>
</html>
