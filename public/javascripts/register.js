/**
 * Created by Alen Bumbulovic on 9/2/2015.
 */


//function for email validation
function checkEmail() {

    var email = document.getElementById('email');
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (!filter.test(email.value)) {
        alert('Please provide a valid email address');
        email.focus();
        return false;
    }
}

//function that checks password
function checkPasswordMatch() {
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("password-retype").value;
    var ok = true;

    if (pass1 != pass2) {
        alert("Passwords Do not match");
        pass1.focus();
        pass2.focus();
        ok = false;
    }else if(pass1.length < 6){
        alert("Password must be at least 6 characters long!");
        ok = false;
        pass1.focus();

    }
    else {

    }
    return ok;
}

//a function that checks if name and lastname contain only letters
function checkName(){
    var letters = /^[A-Za-z]+$/;
    var name = document.getElementById("name");
    var lastname = document.getElementById("last-name");

    if(name.value.match(letters) && lastname.value.match(letters)) {
        return true;
    } else  {
        alert('Name and Last Name must have alphabet characters only!');
        name.focus();
        lastname.focus();
        return false;
    }

}

//function that checks if name or lastname is longer than one character
function checkNameLength(){
    var name = document.getElementById("name").value;
    var lastname = document.getElementById("last-name").value;

    if(name.length > 1 && lastname.length > 1){
        return true;
    }else{
        alert('Name and Last Name must contain at least 2 letters!');
        name.focus();
        lastname.focus();
        return false;
    }
}
//a method that checks the length of phone number
function numberLength(){
    var number = document.getElementById("phone-number").value;

    if(number.length < 16 && number.length < 1){
        return true;
    }else{
        alert("Phone Number can't be longer than 15 digits!")
        number.focus();
        return false;
    }
}

//a function that calls all the other functions
function call() {
    checkEmail();
    checkPasswordMatch();
    checkName();
    checkNameLength();
    numberLength();


}