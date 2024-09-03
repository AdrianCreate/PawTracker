<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="DatabaseConnection.*" %>
<%@ page import="org.apache.commons.text.StringEscapeUtils" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Pet</title>
</head>
<body>
    <div id="updatePet">
        <%
            
            int petId = Integer.parseInt(request.getParameter("petId"));
        	PetHandler petHandler = new PetHandler();
        	Pet pet = petHandler.getPetById(petId);
        
            String newName = StringEscapeUtils.escapeHtml4(request.getParameter("newName"));
            String newSexStr = request.getParameter("newSex");
            char newSex = newSexStr.charAt(0);
            String newType = StringEscapeUtils.escapeHtml4(request.getParameter("newType"));
            String newBreed = StringEscapeUtils.escapeHtml4(request.getParameter("newBreed"));
            String newColour = StringEscapeUtils.escapeHtml4(request.getParameter("newColour"));
            String newDateStr = request.getParameter("newBirthday");
            Date newDate_of_birth = Date.valueOf(newDateStr);
            String newDistinctiveSigns = StringEscapeUtils.escapeHtml4(request.getParameter("newDistinctiveSigns"));
            String newMedication = StringEscapeUtils.escapeHtml4(request.getParameter("newMedication"));
			
            
            // Update pet information in the database
            		Pet updatedPet = new Pet(pet);
                    updatedPet.setName(newName);
                    updatedPet.setSex(newSex);
                    updatedPet.setType(newType);
                    updatedPet.setBreed(newBreed);
                    updatedPet.setColour(newColour);
                    updatedPet.setDateOfBirth(newDate_of_birth);
                    updatedPet.setDistinctiveSigns(newDistinctiveSigns);
                    updatedPet.setMedication(newMedication);
                    

                 
                    petHandler.updatePet(updatedPet);
            
            response.sendRedirect("Dashboard.jsp");
        %>
        
    </div>
</body>
</html>
