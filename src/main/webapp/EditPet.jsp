<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="DatabaseConnection.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Pet</title>
    <link rel="stylesheet" href="css/editPetStyle.css" type="text/css" />
</head>
<body>
    <div id="editPet" class="main-div">
        <%
            int petId = Integer.parseInt(request.getParameter("petId"));
        
            PetHandler petHandler = new PetHandler();
            Pet pet = petHandler.getPetById(petId);

            if (pet != null) {
        %>
        
        <form action="UpdatePet.jsp" method="post" class="editForm">
        <h1>Edit your pet's information</h1>
        <div class="inputDiv">
            <label for="newName">Name:</label>
            <input type="text" id="newName" name="newName" value="<%=pet.getName() %>"><br>
		</div>
		<div>
           	<label>Sex:</label>
			<input type="radio" id="male" name="newSex" value="M" <%= (pet.getSex() == 'M') ? "checked" : "" %>>
			<label for="male">Male</label>

			<input type="radio" id="female" name="newSex" value="F" <%= (pet.getSex() == 'F') ? "checked" : "" %>>
			<label for="female">Female</label><br>
		</div>
		<div class="inputDiv">
            <label for="newType">Type:</label>
             <select id="newType" name="newType">
		        <option value="Dog" <%= "Dog".equals(pet.getType()) ? "selected" : "" %>>Dog</option>
		        <option value="Cat" <%= "Cat".equals(pet.getType()) ? "selected" : "" %>>Cat</option>
		        <option value="Hamster" <%= "Hamster".equals(pet.getType()) ? "selected" : "" %>>Hamster</option>
		        <option value="Parrot" <%= "Parrot".equals(pet.getType()) ? "selected" : "" %>>Parrot</option>
		    </select><br>
		</div>
<div class="inputDiv">
            <label for="newBreed">Breed:</label>
            <input type="text" id="newBreed" name="newBreed" value="<%=pet.getBreed() %>"><br>
</div>
<div class="inputDiv">
            <label for="newColour">Colour:</label>
            <input type="text" id="newColour" name="newColour" value="<%=pet.getColour() %>"><br>
</div>
<div class="inputDiv">
            <label for="newBirthday">Date of birth</label>
            <input type="date" id="newBirthday" name="newBirthday" value="<%= (pet.getDateOfBirth() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(pet.getDateOfBirth()) : "" %>"><br>
</div>
<div class="inputDiv">
            <label for="newDistinctiveSigns">Distinctive signs:</label>
            <input type="text" id="newDistinctiveSigns" name="newDistinctiveSigns" value="<%=pet.getDistinctiveSigns() %>"><br>
</div>
<div class="inputDiv">
            <label for="newMedication">Medication:</label>
            <input type="text" id="newMedication" name="newMedication" value="<%=pet.getMedication() %>"><br>
</div>

<div class="buttonDiv">
            <input type="hidden" name="petId" value="<%=pet.getId()%>">
            <button type="submit">Update</button>
            </div>
        </form>
        <%
            } else {
                response.sendRedirect("Dashboard.jsp");
            }
        %>
    </div>
</body>
</html>
