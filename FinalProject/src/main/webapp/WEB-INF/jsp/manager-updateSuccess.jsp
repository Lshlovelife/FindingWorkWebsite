
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
Info changed successfully.
<br><a href="${contextPath}/manager/dashboard.htm">Back</a>
</body>
</html>
