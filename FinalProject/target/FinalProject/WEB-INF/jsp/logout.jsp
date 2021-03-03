
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h1>LOGOUT SUCCESS</h1>
<br>
<a href = "${contextPath}/login.htm">Return To The Home Page</a><br>
</body>
</html>
