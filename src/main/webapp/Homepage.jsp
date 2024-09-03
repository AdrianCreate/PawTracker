<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/homepageStyles.css" type="text/css" />
<title>Home Page</title>
</head>
<body>
	<div class = "box">
	 	<h1>Welcome to Paw Tracker</h1>
	    <p>Paw Tracker, the solution to your pet getting lost! </p> 
    	<p>Find your pet as soon as possible or help other pet owners find theirs faster! </p>
    	<div class = "links">
    	<%
    		// Check if the user is logged in
    		boolean isLoggedIn = session.getAttribute("userId") != null;
		%>
     		<% if (isLoggedIn) { %>
        	<a href="Dashboard.jsp">Go to Dashboard</a>
        	<a href="SignOut.jsp">Sign Out</a>
    	<% } else { %>
        	<a href="UserRegistration.jsp">Register Now</a>
        	<a href="Login.jsp">Log In Now</a>
    	<% } %>
    	
    </div>
     <div>
	   <img src="https://d.newsweek.com/en/full/1898130/dog-cat-under-sheet.jpg" alt="cat&dog">
	   <img src="https://hips.hearstapps.com/hmg-prod/images/cat-instagram-captions-64ff2dfa47e9a.jpg?crop=0.8888888888888888xw:1xh;center,top&resize=1200:*" alt="Cute Cat">
	   <img src="https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg" alt="Dogs">
	   <img src="https://media.istockphoto.com/id/1445196818/photo/group-of-cute-pets-on-white-background-banner-design.jpg?s=612x612&w=0&k=20&c=TFz9WoJfr7o_9VhuUA4XM6B4BC3gQ_TmA2C8Xe372C8=" alt="Multiple pets">
	</div>
</body>
</html>