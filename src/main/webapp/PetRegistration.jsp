<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/addPetStyles.css" type="text/css" />
<title>Pet Registration</title>
</head>
<body>
<div class="main-div">
	<form action="PetRegisterServlet" method="post" class="editForm">
	<h1>Register your pet!</h1>
	<div class="columns">
		<div class="item">
			<label for="petName"> Name<span class="required">*</span></label>
			<input id="petName" type="text" name="petName" required/>
		</div>
		<label>Sex<span class="required">*</span></label>
    		<div>
      			<label><input type="radio" value="M" name="petSex" required> <span>Male</span></label>
      			<label><input type="radio" value="F" name="petSex" required> <span>Female</span></label>
    		</div>
		<div class="item">
			<label for="petType"> Type<span class="required">*</span></label>
			  <select id="petType" name="petType" required>
			        <option value="Dog">Dog</option>
			        <option value="Cat">Cat</option>
			        <option value="Hamster">Hamster</option>
			        <option value="Parrot">Parrot</option>
			    </select><br>
		</div>
		<div class="item">
			<label for="breed"> Breed<span class="required">*</span></label>
			<input id="breed" type="text" name="breed" required/>
		</div>
		<div class="item">
			<label for="colour"> Colour<span class="required">*</span></label>
			<input id="colour" type="text" name="colour" required/>
		</div>
		<div class ="item">
      		<label for="petBirthDate"> Date of birth<span class="required">*</span></label>
      		<input id="petBirthDate" type ="date" name = "petBirthDate" required/>
    	</div>
		<div class="item">
			<label for="distinctiveSigns"> Distinctive signs</label>
			<input id="distinctiveSigns" type="text" name="distinctiveSigns" />
		</div>
		<div class="item">
			<label for="medication"> Medication</label>
			<input id="medication" type="text" name="medication"/>
		</div>
		<div class="buttonDiv">
    		<button id="myButton">Register</button>
  		</div>
	</div>
	</form>
</div>	
</body>
</html>