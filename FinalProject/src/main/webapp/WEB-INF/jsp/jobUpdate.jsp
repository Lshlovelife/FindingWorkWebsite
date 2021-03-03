<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jobUpdate</title>
</head>
<body>
<c:set var="jid" value="${requestScope.jid}"/>
Job Info:
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="job" value="${requestScope.job}" />
Basic Info:<br>
<form action="${contextPath}/updateJobInfoSuccess" method="POST">
    Title: <input type="text" name="title" value="${job.title}"/><br>
    Company Name: <input type="text" name="companyName" value="${job.companyName}"/><br>
    Type Of Job: <input type="text" name="typeOfJob" value="${job.typeOfJob}"/><br>
    Description: <input type="text" name="description" value="${job.description}"/><br>
    Number of Position: <input type="text" name="numberOfPosition" value="${job.numberOfPosition}"/><br>
    <input type="submit" name="action" value="Update"/>
    <input type="submit" name="action" value="Delete"/>
    <br>
</form>
</body>
</html>
