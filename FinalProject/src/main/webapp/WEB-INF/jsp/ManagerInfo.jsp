
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Account Info:
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="manager" value="${sessionScope.user}" />
Basic Info:<br>
<form action="${contextPath}/ManagerInfo.htm?action=modify-info" method="POST">
    Name: <input type="text" name="name" value="${manager.name}"/><br>
    Age: <input type="text" name="age" value="${manager.age}"/><br>
    Phone: <input type="text" name="phone" value="${manager.phone}"/><br>
   Address: <input type="text" name="address" value="${manager.address}"/><br>
   email: <input type="text" name="email" value="${manager.email}"/><br>
    <input type="submit" name="Save Change" value="submit"/><br>
</form>
</body>
</html>
