
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Account Info:
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="user" value="${sessionScope.user}" />
Basic Info: <br>
<form action="${contextPath}/EmployeeInfo.htm?action=modify-info" method="POST">
    Name: <input type="text" name="name" value="${user.name}"/><br>
    Age: <input type="text" name="age" value="${user.age}"/><br>
    Phone: <input type="text" name="phone" value="${user.phone}"/><br>
    Address: <input type="text" name="address" value="${user.address}"/><br>
    email: <input type="text" name="email" value="${user.email}"/><br>
    <input type="submit" name="Save Change" value="submit"/><br>
</form>
</body>
</html>
