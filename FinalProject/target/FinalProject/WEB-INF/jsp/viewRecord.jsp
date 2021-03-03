<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var = "name" value="${sessionScope.user.getName()}"/>
<nav>
    <a href="${contextPath}/EmployeeInfo.htm">Information</a>
    <a href="${contextPath}/viewAllJob.htm">View job</a>
    <a href="${contextPath}/viewRecord.htm?uid=${uid}">Application record</a>
    <a href="${contextPath}/logout.htm">Logout</a>

</nav>
<h1>Hello ${name}</h1>
<table border="1">
    <tr>
        <td>Job Title</td>
        <td>Job Type</td>
        <td>Company Name</td>
        <td>Description</td>
        <td>Operation</td>
    </tr>

<c:set var="record" value="${sessionScope.record}"/>
<c:forEach var="application" items="${record}">
    <tr>
        <c:set var="aid" value="${application.getApplicationID()}"/>
        <td>${application.getJob().getTitle()}</td>
        <td>${application.getJob().getTypeOfJob()}</td>
        <td>${application.getJob().getCompanyName()}</td>
        <td>${application.getJob().getDescription()}</td>
        <td><a href = "${contextPath}/deleteApplication?aid=${aid}"><button>WithDraw</button></a></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
