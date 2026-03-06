let nameValidator = /^[a-zA-Z]{3,}'?$/;
let emailValidator = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

// Function to validate the name input
function validate_name() {
    if (!nameValidator.test(document.getElementById("name").value)) {
        document.getElementById("error-name").innerHTML = "Please enter a valid name. Minimum 3 characters, only letters and an optional apostrophe.";
        return false;
    }
    document.getElementById("error-name").innerHTML = "";
    return true;
}
// function to validate the email input
function validate_email() {
    if (!emailValidator.test(document.getElementById("email").value)) {
        document.getElementById("error-email").innerHTML = "Please enter a valid email address.";
        return false;
    }
    document.getElementById("error-email").innerHTML = "";
    return true;
}

// function to validate the questions input
function validate_question() {
    
    for (let i = 0; i < 3; i++) {
        if (document.getElementById(`question${i + 1}`).value.checked === false) {
            document.getElementById(`error-question${i + 1}`).innerHTML = "Please answer the question.";
            return false;
        }
        document.getElementById("error-question" + (i + 1)).innerHTML = "";
    }
    return true;
}

function validate_form() {
    if (!(validate_name() && validate_email() && validate_question())) {
        document.getElementById("button-error").innerHTML = "Please correct the errors before submitting the form.";
        return;
    }
    document.getElementById("button-error").innerHTML = "";
}   


document.querySelector("button").addEventListener("click", validate_form);
document.getElementById("name").addEventListener("input", validate_name);
document.getElementById("email").addEventListener("input", validate_email);
document.getElementById("question1").addEventListener("change", validate_question);
document.getElementById("question2").addEventListener("change", validate_question);
document.getElementById("question3").addEventListener("change", validate_question);