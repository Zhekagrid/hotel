<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 29.03.2022
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>com.hrydziushka.finalproject.main.Main</title>
    ${sessionScope.USER}
</head>
<body>
<jsp:include page="header.jsp"/>
USER =${user}
${sessionScope.role}


<form method="post" action="${pageContext.request.contextPath}/uploadController" enctype="multipart/form-data">
    <input type="file" name="upload_file" style="width: 100px"/>
    <input type="submit" value="ok"/>
</form>
<img src="${requestScope.new_file1}" width="150" height="auto"/>
<img src="${requestScope.new_file2}" width="150" height="auto"/>
<c:if test="${sessionScope.role=='ADMIN'}">
    ${requestScope.page}

    <div class="container-fluid">
        <div class="row">
            <main class="col m-1 mt-1">

                <div class="row">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="label.user.id"/></th>
                            <th scope="col"><fmt:message key="label.user.login"/></th>
                            <th scope="col"><fmt:message key="label.user.email"/></th>
                            <th scope="col"><fmt:message key="label.user.phoneNumber"/></th>
                            <th scope="col"><fmt:message key="label.user.balance"/></th>
                            <th scope="col"><fmt:message key="label.user.status"/></th>
                            <th scope="col"><fmt:message key="label.user.role"/></th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <form method="post">
                                <input type="hidden" name="selectedUserId" value="${user.id}">

                                <tr>

                                    <th scope="row">${user.id}</th>
                                    <td>${user.login}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phoneNumber}</td>
                                    <td>${user.balance}</td>

                                    <td>
                                        <select name="selectedUserStatus" class="form-select"
                                                aria-label="Default select example">
                                            <option value="BLOCKED" ${user.userStatus == 'BLOCKED' ? ' selected' : ''}>
                                                <fmt:message key="user.status.blocked"/>
                                            </option>
                                            <option value="ACTIVE" ${user.userStatus == 'ACTIVE' ? ' selected' : ''}>
                                                <fmt:message key="user.status.active"/>
                                            </option>

                                        </select>
                                    </td>
                                    <td>
                                        <select name="selectedUserRole" class="form-select"
                                                aria-label="Default select example">
                                            <option value="USER" ${user.userRole == 'USER' ? ' selected' : ''}>
                                                <fmt:message key="user.role.user"/>
                                            </option>

                                            <option value="ADMIN" ${user.userRole== 'ADMIN' ? ' selected' : ''}>
                                                <fmt:message key="user.role.admin"/>
                                            </option>

                                        </select>
                                    </td>
                                    <td>


                                        <span><button class="btn btn-outline-primary" type="submit"
                                                      formaction="${pageContext.request.contextPath}/controller?command=save_user_changes"><fmt:message
                                                key="label.save"/> </button></span>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="navigation for items">
                        <ul class="pagination justify-content-center mt-3 mb-4">
                            <c:if test="${requestScope.page != 1}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${requestScope.page-1}">
                                        <fmt:message key="pages.prev"/>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.pagesCount}" var="i">
                                <c:choose>
                                    <c:when test="${requestScope.page eq i}">
                                        <li class="page-item active">
                                            <a class="page-link"> ${i} <span class="sr-only"></span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${i}">
                                                    ${i}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${requestScope.page lt requestScope.pagesCount}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/controller?command=go_to_home_page&usersCountPerPage=${requestScope.usersCountPerPage}&page=${requestScope.page+1}">
                                        <fmt:message key="pages.next"/>
                                    </a>
                                </li>
                            </c:if>

                            <form method="get" action="${pageContext.request.contextPath}/controller">

                                <input type="hidden" name="command" value="go_to_home_page"/>
                                <div class="form-group col-md-4">

                                    <label for="records"><fmt:message key="pages.itemsCount"/></label>

                                    <select id="records" name="usersCountPerPage">
                                        <option value="1"${requestScope.usersCountPerPage == 1 ? ' selected' : ''}>1
                                        </option>
                                        <option value="2" ${requestScope.usersCountPerPage == 2 ? ' selected' : ''}>2
                                        </option>
                                        <option value="3" ${requestScope.usersCountPerPage == 3 ? ' selected' : ''}>3
                                        </option>
                                    </select>

                                </div>

                                <button type="submit" class="btn btn-primary"><fmt:message key="pages.apply"/></button>

                            </form>


                        </ul>
                    </nav>
                </div>

            </main>
        </div>
    </div>


</c:if>

<jsp:include page="footer.jsp"/>
</body>
</html>
