 function updateButton() {
    // verifica care buton radio e apasat
    var answer = document.querySelector('input[name="answer"]:checked').value;

    // Validate first name, last name, email, and phone number
    var firstName = document.getElementById('fname').value;
    var lastName = document.getElementById('lname').value;
    var email = document.getElementById('email').value;
    var phoneNumber = document.getElementById('phonenr').value;

    var isValidFirstName = (firstName.trim() !== '');
    var isValidLastName = (lastName.trim() !== '');
    var isValidEmail = validateEmail(email);
    var isValidPhoneNumber = validatePhoneNumber(phoneNumber);

    // enable sau disable in functie de raspuns si validare
    var button = document.getElementById('myButton');
    if (answer === 'yes' && isValidFirstName && isValidLastName && isValidEmail && isValidPhoneNumber) {
      button.disabled = false;
      button.classList.add('active');
    } else {
      button.disabled = true;
      button.classList.remove('active');
    }
  }

  function validateEmail(email) {
    // Regex for email validation: ^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$
    var emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return emailRegex.test(email);
  }

  function validatePhoneNumber(phoneNumber) {
    // Regex for phone number validation: ^(\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\s|\.|\-)?([0-9]{3}(\s|\.|\-|)){2}$
    var phoneRegex = /^(\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\s|\.|\-)?([0-9]{3}(\s|\.|\-|)){2}$/;
    return phoneRegex.test(phoneNumber);
  }