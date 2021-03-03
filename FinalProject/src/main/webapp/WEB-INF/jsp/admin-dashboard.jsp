<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<a href="${contextPath}/logout.htm">Logout</a><br>
Admin All User Info
<table border="1">
    <tr>

        <td>UserName</td>
        <td>PassWord</td>
        <td>Type</td>
    </tr>
<c:set var="ul" value="${sessionScope.userList}"/>
<c:forEach var="user" items="${ul}">
    <tr>
        <td>${user.getUname()}</td>
        <td>${user.getUpassword()}</td>
        <td>${user.getType()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
