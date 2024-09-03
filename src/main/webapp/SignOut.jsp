<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>

<%
    // Invalidate the current session to log the user out
    session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
     html, body {
      min-height: 100%;
      background-image: radial-gradient(
    circle farthest-corner at 10% 20%,
    rgba(253, 101, 133, 1) 0%,
    rgba(255, 211, 165, 1) 90%
  );
    
    }
    body {
      width: 100%;
      padding: 20px;
      border-radius: 6px;
      background: #fff;
      box-shadow: 0 0 30px 10px darkblue;
    }
  </style>
    <title>Log Out</title>
</head>
<body>
   <% response.sendRedirect("Homepage.jsp"); %>
</body>
</html>
