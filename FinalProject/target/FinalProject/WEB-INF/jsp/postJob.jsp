<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post job</title>
</head>
<body>
<h1>Post a job here</h1>
<form method="post" action="postJob.htm">
    <span style="display:inline-block;width:200px;text-align:right;">Title:</span> <input type="text" name="title"/><br>
    <span style="display:inline-block;width:200px;text-align:right;">Company Name:</span> <input type="text" name="companyName"/><br>
    <span style="display:inline-block;width:200px;text-align:right;">Type Of Job:</span> <input type="text" name="typeOfJob"/><br>
    <span style="display:inline-block;width:200px;text-align:right;">Description:</span> <input type="text" name="description"/><br>
    <span style="display:inline-block;width:200px;text-align:right;">Number Of Position:</span> <input type="text" name="numberOfPosition"/><br>
    <span style="display:inline-block;width:200px;text-align:right;"></span>  <input type="submit" value="Post"/>
    <input type="hidden" value="${requestScope}">
</form>
</body>
</html>
