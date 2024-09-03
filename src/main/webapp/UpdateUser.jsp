<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="DatabaseConnection.*" %>
<%@ page import="org.apache.commons.text.StringEscapeUtils"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
</head>
<body>
    <div id="updateUser">
        <%
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                response.sendRedirect("Login.jsp");
            } else {
                
                UserHandler userHandler = new UserHandler();
                User user = userHandler.getUserById(userId);

                if (user == null) {
                    response.sendRedirect("Login.jsp");
                } else {
     
                	 String newFirstName = request.getParameter("newFirstName");
                     String newLastName = request.getParameter("newLastName");
                     String newAddress = request.getParameter("newAddress");
                     String newCity = request.getParameter("newCity");
                     String newEmail = request.getParameter("newEmail");
                     String newPhoneNr = request.getParameter("newPhoneNr");
                     String newSexStr = request.getParameter("newSex");
                     char newSex = newSexStr.charAt(0);
                     String newDateStr = request.getParameter("newBirthday");
                     Date newDate_of_birth = Date.valueOf(newDateStr);

                    
                    User updatedUser = new User(user);
                    updatedUser.setFirstName(newFirstName);
                    updatedUser.setLastName(newLastName);
                    updatedUser.setAddress(newAddress);
                    updatedUser.setCity(StringEscapeUtils.escapeHtml4(newCity));
                    updatedUser.setEmail(newEmail);
                    updatedUser.setPhoneNr(newPhoneNr);
                    updatedUser.setSex(newSex);
                    updatedUser.setDateOfBirth(newDate_of_birth);
                    

                 
                    userHandler.updateUser(updatedUser);

                    
                    response.sendRedirect("Dashboard.jsp");
                }
            }
        %>
    </div>
</body>
</html>