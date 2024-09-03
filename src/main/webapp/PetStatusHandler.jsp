<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="DatabaseConnection.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int petId = Integer.parseInt(request.getParameter("petId"));
	boolean isLost = Boolean.parseBoolean(request.getParameter("petStatus"));
	
	PetHandler petHandler = new PetHandler();
	
	if(isLost) {
		//System.out.println("sunt pierdut");
		petHandler.findPet(petId);
	}
	else {
		//System.out.println("sunt acasa");
		petHandler.losePet(petId);
	}
	
	response.sendRedirect("Dashboard.jsp");
	%>
</body>
</html>