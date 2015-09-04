/**
 * Created by Alen Bumbulovic on 9/2/2015.
 */


//function for email validation
function checkEmail() {

    var email = document.getElementById('email');
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (!filter.test(email.value)) {
        document.getElementById('email_error').innerHTML = "Please provide a valid email adress!";
        document.getElementById('submit').disable="true";
        return false;
    }else{
        document.getElementById('email_error').innerHTML = "";
        document.getElementById('submit').disable="false";
        return true;
    }
}
function checkPasswordLength() {

    var pass1 = document.getElementById('password').value;

    var ok = true;

    if(pass1.length < 6){
        document.getElementById('password_error').innerHTML="Password must be at least 6 characters long!";
        document.getElementById('submit').disable="true";
        return false;
    }else {
        document.getElementById('password_error').innerHTML="";
        document.getElementById('submit').disable="false";
            return true;
    }

}
//function that checks password match
function checkPasswordMatch() {
    var pass1 = document.getElementById("password").value;
    var pass2 = document.getElementById("password-retype").value;


    if (pass1 != pass2) {
        document.getElementById('password2_error').innerHTML="Passwords Do not match";
        document.getElementById('submit').disable="true";
        return false;
    }else {
        document.getElementById('password2_error').innerHTML= "";
        document.getElementById('submit').disable="false";
        return true;
    }

}



//a function that checks if name and lastname contain only letters
function checkName(){
    var letters = /^[A-Za-z]+$/;
    var name = document.getElementById("name");

    if(name.value.match(letters) ) {
        document.getElementById('name_error').innerHTML="";
        document.getElementById('submit').disable="false";

        return true;
    } else  {
        document.getElementById('name_error').innerHTML="Name must have alphabet characters only!";
        document.getElementById('submit').disable="true";
        return false;
    }

}

function checkLastname(){
    var letters = /^[A-Za-z]+$/;
    var lastname = document.getElementById("last-name");

    if(lastname.value.match(letters)) {

        document.getElementById('lastname_error').innerHTML="";
        document.getElementById('submit').disable="false";
        return true;
    } else  {
        document.getElementById('lastname_error').innerHTML="Last Name must have alphabet characters only!";
        document.getElementById('submit').disable="true";
        return false;
    }

}


//function that checks if name or lastname is longer than one character
function checkNameLength(){
    var name = document.getElementById("name").value;


    if(name.length > 1){
        document.getElementById('namelength_error').innerHTML="";
        document.getElementById('submit').disable="false";
        return true;
    }else{
       document.getElementById('namelength_error').innerHTML="Name must contain at least 2 letters!";
        document.getElementById('submit').disable="true";
        return false;
    }
}


function checkLastnameLength(){

    var lastname = document.getElementById("last-name").value;

    if(lastname.length > 1){
        document.getElementById('lastnamelength_error').innerHTML="";
        document.getElementById('submit').disable="false";
        return true;
    }else{
        document.getElementById('lastnamelength_error').innerHTML="Lastname must contain at least 2 letters!";
        document.getElementById('submit').disable="true";
        return false;
    }
}

//a method that checks the length of phone number
function numberLength(){
    var number = document.getElementById("phone-number").value;

    if(number.length < 16 && number.value < 0){
        document.getElementById('numberlength_error').innerHTML="";
        document.getElementById('submit').disable="false";
        return true;
    }else{
        document.getElementById('numberlength_error').innerHTML="Number can have 15 digits max!";
        document.getElementById('submit').disable="true";
        return false;
    }
}

function isNumber(){
    var number = document.getElementById("phone-number").value;

    if(/\D/.test(number)){
        document.getElementById('number_error').innerHTML="Number can contain only digits(0-9)!";
        document.getElementById('submit').disable="true";
        return false;
    }
    document.getElementById('number_error').innerHTML="";
    document.getElementById('submit').disable="false";
    return true;
}


//a function that calls all the other functions
function call() {
    checkEmail();
    checkPasswordMatch();
    checkName();
    checkNameLength();
    numberLength();


}