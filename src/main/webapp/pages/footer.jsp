<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>


<html>
<head>
    <title>Footer</title>
</head>
<body>


<footer class="bg-light text-center">

    <div class="container p-4">


        <section class="mb-4">
            <p>
                Hotel...
                <!--todo add description-->
            </p>
        </section>


        <section class="mb-4 ">


            <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=ru">RU</a>

            <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=en">EN</a>

            <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=de">DE</a>


        </section>
    </div>

    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
        © 2022 Copyright:
        <a class="text-dark" href="https://mdbootstrap.com/">Zhekagrid</a>
    </div>


</footer>




</body>
</html>
