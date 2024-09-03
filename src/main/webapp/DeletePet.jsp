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

	Integer userId = (Integer) session.getAttribute("userId");
	UserHandler userHandler = new UserHandler();
	User user = userHandler.getUserById(userId);
	
	if(user!=null) {
		PetHandler petHandler = new PetHandler();
		petHandler.removePetOwnership(userId, petId);
		Pet pet = petHandler.getPetById(petId);
		 if (pet != null) { 
         	petHandler.deletePet(pet);
         } else {
             response.sendRedirect("Dashboard.jsp");
         }
	} else {
		response.sendRedirect("Login.jsp"); // Redirect to login if user is not authenticated
	}
	
	response.sendRedirect("Dashboard.jsp");
            
        %>
</body>
</html>