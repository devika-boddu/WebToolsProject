<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
    <body>
        <h1> SignUp Page</h1>
        <p>Welcome</p>
        <form:form modelAttribute="user" method="post" action = "signUpLogin.htm">
        <table>
        <tr>
		    <td>User Name</td>
		    <td><form:input path="name" size="30" /> <font color="red"><form:errors path="name"/></font></td>
		</tr>
		<tr>
		    <td>Password</td>
		    <td><form:input path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
		</tr>
		<tr>
		    <td>Email Id:</td>
		    <td><form:input path="email" size="30" /> <font color="red"><form:errors path="email"/></font></td>
		</tr>
    	<tr>
		    <td colspan="2"><input type="submit" value="Signup" /></td>
		</tr>
        </table>
        </form:form>
        <form method="get" action="login.htm">
           <button type = "submit">Already have an account?. Click Here</button>
        </form>

        <c:if test="${not empty requestScope.emailAlertMessage}">
            <script>
                showAlert("${requestScope.emailAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
        <c:if test="${not empty requestScope.userNameAlertMessage}">
            <script>
                showAlert("${requestScope.userNameAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
    </body>
</html>
