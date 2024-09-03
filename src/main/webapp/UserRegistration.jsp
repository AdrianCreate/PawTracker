<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="DatabaseConnection.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User registration</title>
 <link rel="stylesheet" href="css/userRegistrationStyles.css" type="text/css" />
 <script src="Button.js"></script>
</head>
<body>
<div class ="box">
  <form action="RegisterServlet" method="post">
  <div class="banner">
    <h1>Paw Tracker Registration</h1>
  </div>
  <div class="columns">
    <div class ="item">
      <label for="fname"> First name<span class="required">*</span></label>
      <input id="fname" type="text" name="fname" required oninput="updateButton()" placeholder="Enter your first name here..." />
    </div>
    <div class ="item">
      <label for="lname"> Last name<span class="required">*</span></label>
      <input id="lname" type="text" name="lname" required oninput="updateButton()" placeholder="Enter your last name here..."/>
    </div>
    <div class ="item">
      <label for="password"> Password<span class="required">*</span></label>
      <input id="password" type ="password" name = "password" required placeholder="Enter a strong password here..."/>
    </div>
    <div class ="item">
      <label for="address">Home Address<span class="required">*</span></label>
      <div class="address-item">
        <input id="street_address" type ="text" name="street_address" placeholder="Street address" required/>
        <input id="building" type ="text" name="building" placeholder="Building" required/>
        <input id="zipcode" type ="text" name="zipcode" placeholder="Zip/Postal code" required/>
      </div>
    </div>
    <div class ="item">
      <label for="city"> City<span class="required">*</span></label>
      <input id="city" type ="text" name = "city" required placeholder="Enter your city of residence here..."/>
    </div>
  <div class="item">
        <label for="email"> Email Address<span class="required">*</span></label>
        <input id="email" type="text" name="email" required oninput="updateButton()" placeholder="Enter your email adress here..." />
      </div>
    <div class ="item">
      <label for="phonenr"> Phone Number<span class="required">*</span></label>
      <input id="phonenr" type="text" name="phonenr" required oninput="updateButton()" placeholder="Enter your phone number here..." />
    </div>
    
    <div class="question">
  <label>Gender<span class="required">*</span></label>
  <div class="question-answer">
    <div>
      <label><input type="radio" value="M" name="sex" required> <span>Male</span></label>
      <label><input type="radio" value="F" name="sex" required> <span>Female</span></label>
    </div>
  </div>
  </div> 
    
  <div class ="item">
      <label for="birthday"> Date of birth<span class="required">*</span></label>
      <input id="birthday" type ="date" name = "birthday" required/>
    </div>
    

  </div>
  <div class="question">
    <label>Subscription type<span class="required">*</span></label>
    <div class="question-answer">
      <div>
        <label><input type="radio" value="none" name="membership" required/> <span>Silver</span></label>
        <label><input type="radio" value="none" name="membership" required/> <span>Gold</span></label>
        <label><input type="radio" value="none" name="membership" required/> <span>Platinum</span></label>
      </div>
    </div>
  </div>
  <div class="question">
    <label>Do you agree to our terms&conditions?<span class="required">*</span></label>
    <div class="question-answer">
      <div>
        <label><input type="radio" value="no" name="answer" onchange="updateButton()" checked> No</label>
        <label><input type="radio" value="yes" name="answer" onchange="updateButton()"> Yes</label>
      </div>
    </div>
  </div>
  <div class="btn-block">
    <button id="myButton">Register</button>
  </div>

</form>
</div>
</body>
</body>
</html>