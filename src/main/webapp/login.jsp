<%--
  Created by IntelliJ IDEA.
  User: nolandseigler
  Date: 2/26/20
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login To The App</title>
    <jsp:include page="partials/style.jsp" />
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <form action="/login.jsp" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password">
        <input type="submit" value="Submit">
    </form>
    <%
        if(request.getParameter("username") != null && request.getParameter("password") != null) {
            if(request.getParameter("username").equals("admin") && request.getParameter("password").equals("admin_password")) {
                response.sendRedirect("/profile.jsp");
            } else {
                response.sendRedirect("/login.jsp");
            }
        }

    %>
    <jsp:include page="partials/scripts.jsp" />
<%--    <c:choose>--%>
<%--        <c:when test="${request.getParameter('username') != null && request.getParameter('password') != null}">--%>
<%--            <c:when test="${request.getParameter('username').equals('admin') && request.getParameter('password').equals('a')}">--%>
<%--&lt;%&ndash;                <c:redirect url="/profile.jsp"/>&ndash;%&gt;--%>
<%--                <h1>Case 1</h1>--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--&lt;%&ndash;                <c:redirect url="/login.jsp"/>&ndash;%&gt;--%>
<%--                <h1>Case 2</h1>--%>
<%--            </c:otherwise>--%>
<%--        </c:when>--%>
<%--        <c:otherwise><h1>Case 3</h1></c:otherwise>--%>
<%--    </c:choose>--%>
</body>
</html>
