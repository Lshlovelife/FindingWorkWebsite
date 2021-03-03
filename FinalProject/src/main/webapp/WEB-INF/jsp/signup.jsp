
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Signup</title>
</head>
<body>
<h2>Create an account</h2>
<form action="create.htm" method="get">
    <table>
        <tr>
            <td>New Username:</td>
            <td><input type = "text" id = "newUsername" required="required" name ="newUsername" />
                <button type="button" onclick="ajaxEvent()">Check UserName</button>
            </td>

        </tr>
        <tr>
            <td>New Password:</td>
            <td><input type ="text" required="required" name ="newPassword" /></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td><input type="radio" name="role" value="manager"/>Manager
                <input type="radio" name="role" value="employee"/>Employee
                <input type="radio" name="role" value="admin"/>Admin
            </td>
        </tr>
        <tr>
            <td><input type="submit" value = "Create account"> </td>
        </tr>
    </table>
     <div style="color: red " id="info"></div>
</form>
<script type="text/javascript">
    function ajaxEvent() {
     // var username = document.getElementById("newUsername").value;
     // console.log(username);
     // alert(username);
        var xmlHttp;
        xmlHttp=new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                document.getElementById("info").innerHTML = xmlHttp.responseText;
            }
        }

        var username = document.getElementById("newUsername").value;
        console.log(username);
        xmlHttp.open("GET", "checkUserExistence.htm?username=" + username, true);
        xmlHttp.send();
    }

</script>
</body>
</html>
