<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #F2F2F2;
                color: #333;
            }
            
            h1 {
                text-align: center;
                margin-top: 50px;
            }
            
            table {
                margin: 0 auto;
                border-collapse: collapse;
                background-color: white;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            
            td {
                padding: 10px;
                text-align: left;
                vertical-align: middle;
                border: 1px solid #CCC;
            }
            
            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 8px;
                border: 1px solid #CCC;
                border-radius: 4px;
                box-sizing: border-box;
                margin-bottom: 10px;
            }
            
            input[type="submit"] {
                background-color: #333;
                color: #FFF;
                border: none;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
            }
            
            input[type="submit"]:hover {
                background-color: #555;
            }
            
            button[type="submit"] {
                background-color: transparent;
                color: #333;
                border: none;
                cursor: pointer;
            }
            
            button[type="submit"]:hover {
                text-decoration: underline;
            }
            
            .error {
                color: red;
            }
            
            .alert {
                background-color: #FFD966;
                padding: 10px;
                margin-top: 10px;
                border-radius: 4px;
            }
        </style>
    </head>
    <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
    <body>
        <h1> Login Page</h1>
        <p>Welcome</p>
        <form:form modelAttribute="user" method="post" action = "home.htm">
        <table>
        <tr>
		    <td>User Name</td>
		    <td><form:input path="name" size="30" /> <font color="red"><form:errors path="name"/></font></td>
		</tr>
		<tr>
		    <td>Password</td>
		    <td><form:input path="password" type="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
		</tr>
		
    	<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
        </table>
        </form:form>
        <form method="get" action="forgotPassword.htm">
           <button type = "submit">Forgot Password.</button>
        </form>
        <form method="get" action="signup.htm">
           <button type = "submit">New have an account?. Click Here</button>
        </form>
        <form method="get" action="adminLogin.htm">
            <button type = "submit">Login as Admin</button>
         </form>

         <c:if test="${not empty requestScope.passwordAlertMessage}">
            <script>
                showAlert("${requestScope.passwordAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
        <c:if test="${not empty requestScope.userNameAlertMessage}">
            <script>
                showAlert("${requestScope.userNameAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
    </body>
</html>
