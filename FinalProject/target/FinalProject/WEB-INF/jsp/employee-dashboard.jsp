<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var = "name" value="${sessionScope.user.getName()}"/>
<c:set var="uid" value="${sessionScope.user.getUserId()}"/>
<h1>Hello ${name}</h1>
<nav>
    <a href="${contextPath}/EmployeeInfo.htm">Information</a>
    <a href="${contextPath}/viewAllJob.htm">View job</a>
    <a href="${contextPath}/viewRecord.htm?uid=${uid}">Application record</a>
    <a href="${contextPath}/logout.htm">Logout</a>

</nav>

</body>
</html>
