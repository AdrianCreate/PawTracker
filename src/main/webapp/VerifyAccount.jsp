<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 id="status">Logging in</h1>
<%
User user = null;

UserHandler userHandler = new UserHandler();
user = userHandler.loginUser(request.getParameter("email"), request.getParameter("password"));
if(user != null) {
	session.setAttribute("userId", user.getId());
	response.sendRedirect("Dashboard.jsp");
} else {
	%>
	<script>
    // Display a login failure message
    var statusElement = document.getElementById("status");
    statusElement.innerHTML = "Log in failed";

    // Redirect to Homepage.jsp after a 5-second delay
    setTimeout(function() {
        window.location.href = "Homepage.jsp";
    }, 5000);
</script>
<%
}
%>
</body>
</html>