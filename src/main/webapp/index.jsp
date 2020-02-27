<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= "some title" %></title>
    <jsp:include page="partials/style.jsp" />
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <c:if test="true">
        <h1>Variable names should be very descriptive</h1>
    </c:if>
    <c:if test="false">
        <h1>single letter variable names are good</h1>
    </c:if>
    <a href="login.jsp">Login</a>
    <jsp:include page="partials/scripts.jsp" />
</body>
</html>
