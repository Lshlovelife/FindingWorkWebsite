<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<form method="post" action="${contextPath}/applySuccess" enctype="multipart/form-data">
    apply job
    Description:<input type = "text" name = "description" /></br>
    Graduation School:<input type="text" name="graduationSchool"/><br>
    Resume:<input type="file" name="uploadFile"/><br>
    <input type="submit" value="submit">
</form>

</body>
</html>
