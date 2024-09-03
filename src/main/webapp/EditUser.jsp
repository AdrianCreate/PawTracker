<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/editUserStyles.css" type="text/css" />
    <title>Edit User</title>
</head>
<body>
    <div class="main-div">
        <%
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                response.sendRedirect("Login.jsp");
            } else {
                // Take the existing user details from the database
                UserHandler userHandler = new UserHandler();
                User user = userHandler.getUserById(userId);

                if (user == null) {
                    response.sendRedirect("Login.jsp");
                } else {
        %>
                    <form action="UpdateUser.jsp" method="post" class="editForm">
                       <h1>Edit your profile details</h1>
                       <div class="item">
                        <label for="newFirstName">First Name:</label>
                        <input type="text" id="newFirstName" name="newFirstName" value="<%=user.getFirstName() %>"><br>
						</div>
						
						<div class="item">
                        <label for="newLastName">Last Name:</label>
                        <input type="text" id="newLastName" name="newLastName" value="<%=user.getLastName() %>"><br>
						</div>
						
						<div class="item">
     					<label for="address">Home Address:</label>
      					<input type="text" id="newAddress" name="newAddress" value="<%=user.getAddress() %>"><br>
      					</div>
      					
      					<div class="item">	
                        <label for="newCity">City:</label>
                        <input type="text" id="newCity" name="newCity" value="<%=user.getCity() %>"><br>
                        </div>
                        
                        <div class="item">
                        <label for="newEmail">Email:</label>
                        <input type="text" id="newEmail" name="newEmail" value="<%=user.getEmail() %>"><br>
                        </div>
                        
                        <div class="item">
                        <label for="newPhoneNr">Phone Number:</label>
                        <input type="text" id="newPhoneNr" name="newPhoneNr" value="<%=user.getPhoneNr() %>"><br>
                        </div>
                        
                       <div>
           				<label>Sex:</label>
						<input type="radio" id="male" name="newSex" value="M" <%= (user.getSex() == 'M') ? "checked" : "" %>>
						<label for="male">Male</label>

						<input type="radio" id="female" name="newSex" value="F" <%= (user.getSex() == 'F') ? "checked" : "" %>>
						<label for="female">Female</label><br>
						</div>

                        
                        <div class="item">
                        <label for="newBirthday">Date of birth</label>
                        <input type="date" id="newBirthday" name="newBirthday" value="<%=user.getDateOfBirth() %>"><br>
                        </div>
					<div class="buttonDiv">
                       <button type="submit">Update</button>
                    </div>
                    </form>
        <%
                }
            }
        %>
    </div>
</body>
</html>