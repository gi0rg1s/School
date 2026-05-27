let nameValidator = /^[a-zA-Z]{3,}'?$/;
let emailValidator = /^\d{1}[a-zA-Z]{1}$/;

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
function validate_class() {
    if (!emailValidator.test(document.getElementById("class").value)) {
        document.getElementById("error-class").innerHTML = "Please enter a valid  class.";
        return false;
    }
    document.getElementById("error-class").innerHTML = "";
    return true;
}

// function to validate the questions input
function validate_question1() {
    let a = document.getElementById("madrid")
    let b = document.getElementById("paris")
    let c = document.getElementById("berlin")
    let d = document.getElementById("rome")
    if (!a.checked && !b.checked && !c.checked && !d.checked) {
        document.getElementById("error-question1").innerText = "Seleziona una risposta per la domanda 1.";
        return false;
    }
    document.getElementById("error-question1").innerText = "";
    return true;
}

function validate_question2() {
    let a = document.getElementById("venere");
    let b = document.getElementById("marte");
    let c = document.getElementById("giove");
    let d = document.getElementById("saturno");
    if (!a.checked && !b.checked && !c.checked && !d.checked) {
        document.getElementById("error-question2").innerText = "Seleziona una risposta per la domanda 2.";
        return false;
    }
    document.getElementById("error-question2").innerText = "";
    return true;
}

function validate_question3() {
    let a = document.getElementById("25")
    let b = document.getElementById("30")
    let c = document.getElementById("35")
    let d = document.getElementById("40")
    if (!a.checked && !b.checked && !c.checked && !d.checked) {
        document.getElementById("error-question3").innerText = "Seleziona una risposta per la domanda 3.";
        return false;
    }
    document.getElementById("error-question3").innerText = "";
    return true;
}

function validate_form() {
    if (!(validate_name() && validate_class() && validate_question1() && validate_question2() && validate_question3())) {
        document.getElementById("button-error").innerText = "Per favore correggi gli errori prima di inviare il form.";
        return;
    }
    document.getElementById("button-error").innerHTML = "";
    document.getElementById("form-inviato").innerHTML += `<p>Nome: ${document.getElementById("name").value}</p>
                                                         <p>Classe: ${document.getElementById("class").value}</p>
                                                         <p>Punteggio: ${count_correct_answers()} / 4.5</p><hr>`;
}   

function count_correct_answers() {
    let points = 0;
    if (document.getElementById("paris").checked) points += 1.5;
    if (document.getElementById("marte").checked) points += 1.5;
    if (document.getElementById("30").checked) points += 1.5;
    return points
}

document.querySelector("button").addEventListener("click", validate_form)
document.getElementById("name").addEventListener("input", validate_name)
document.getElementById("class").addEventListener("input", validate_class)

//question1
document.getElementById("madrid").addEventListener("change", validate_question1)
document.getElementById("berlin").addEventListener("change", validate_question1)
document.getElementById("paris").addEventListener("change", validate_question1)
document.getElementById("rome").addEventListener("change", validate_question1)

//question2
document.getElementById("venere").addEventListener("change", validate_question2)
document.getElementById("marte").addEventListener("change", validate_question2)
document.getElementById("giove").addEventListener("change", validate_question2)
document.getElementById("saturno").addEventListener("change", validate_question2)

//question3
document.getElementById("25").addEventListener("change", validate_question3)
document.getElementById("30").addEventListener("change", validate_question3)
document.getElementById("35").addEventListener("change", validate_question3)
document.getElementById("40").addEventListener("change", validate_question3)