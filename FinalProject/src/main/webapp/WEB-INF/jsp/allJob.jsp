<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Job</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1>All Job Info</h1>
<table border="1">
    <tr>
        <td>jobID</td>
        <td>Title</td>
        <td>CompanyName</td>
        <td>Type Of Job</td>
        <td>Description</td>
        <td>Number Of Position</td>
        <td>Operation</td>
        <td>PDF</td>
    </tr>
    <c:set var="ajl" value="${sessionScope.allJobList}"/>
    <c:forEach var="job" items="${ajl}">
        <tr>
            <td>${job.getJobId()}</td>
            <td>${job.getTitle()}</td>
            <td>${job.getCompanyName()}</td>
            <td>${job.getTypeOfJob()}</td>
            <td>${job.getDescription()}</td>
            <td>${job.getNumberOfPosition()}</td>
            <<td><a href = "${contextPath}/applyJob.htm?jobId=${job.getJobId()}" >
            <button>Apply</button></a></td>
            <<td><a href = "${contextPath}/pdfView.htm?title=${job.getTitle()}&cn=${job.getCompanyName()}&toj=${job.getTypeOfJob()}&des=${job.getDescription()}&num=${job.getNumberOfPosition()}" >
            <button>PDFView</button></a></td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
