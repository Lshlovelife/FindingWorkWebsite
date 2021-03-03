<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav>
    <a href="${contextPath}/ManagerInfo.htm">Information</a>
    <a href="${contextPath}/PostJob.htm">Post Job</a>
    <a href="${contextPath}/logout.htm">Logout</a>
</nav>
view All info
<table border="1">
    <tr>
        <td>Applicant Name</td>
        <td>Applicant Phone</td>
        <td>Description</td>
        <td>Graduation School</td>
        <td>Resume</td>
    </tr>
    <c:set var="ja" value="${sessionScope.jobApplication}"/>
    <c:forEach var="application" items="${ja}">
        <tr>
            <td>${application.getEmployee().getName()}</td>
            <td>${application.getEmployee().getPhone()}</td>
            <td>${application.getDescription()}</td>
            <td>${application.getGraduationSchool()}</td>

<%--                ${application.getResumePath()}--%>
<%--                ${application.getResumePath().replace("\\", "/")}--%>

            <td><a href="${contextPath}/downloadResume.htm?path=${application.getResumePath().replace("\\", "/")}&aName=${application.getEmployee().getName()}">
            <button>Download Resume</button></a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
