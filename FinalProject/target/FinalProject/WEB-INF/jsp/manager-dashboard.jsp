<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav>
    <a href="${contextPath}/ManagerInfo.htm">Information</a>
    <a href="${contextPath}/PostJob.htm">Post Job</a>
    <a href="${contextPath}/logout.htm">Logout</a>
</nav>
manager<br>
JobList:
<%--<form method="post" action="${contextPath}/updateJobInformation.htm">--%>
    <table border="1">
        <tr>
<%--            <td>jobID</td>--%>
            <td>Title</td>
            <td>CompanyName</td>
            <td>Type Of Job</td>
            <td>Description</td>
            <td>Number Of Position</td>
            <td>Operation</td>

        </tr>

        <c:set var="jl" value="${sessionScope.jobList}"/>
        <c:forEach var="job" items="${jl}">
            <tr>
<%--                <td>${job.getJobId()}</td>--%>
                <td>${job.getTitle()}</td>
                <td>${job.getCompanyName()}</td>
                <td>${job.getTypeOfJob()}</td>
                <td>${job.getDescription()}</td>
                <td>${job.getNumberOfPosition()}</td>
                <td><a href = "${contextPath}/updateJobInformation.htm?jobId=${job.getJobId()}" >
                    <button>Update</button></a>
                    <a href = "${contextPath}/viewApplication.htm?jobId=${job.getJobId()}" >
                        <button>View Application</button></a>
                   </td>

            </tr>

        </c:forEach>
    </table>


<%--</form>--%>

</body>
</html>
