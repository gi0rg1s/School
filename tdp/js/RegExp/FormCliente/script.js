//validating form pattern
let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
let phonePattern = /^\d{10}$/;
let namePattern = /^[a-zA-Z\s]{3,}$/;

const nomeInput = document.getElementById("nome");
const emailInput = document.getElementById("email");
const telefonoInput = document.getElementById("telefono");
const budgetInput = document.getElementById("budget");
const serviziSelect = document.getElementById("servizi");
const submitButton = document.getElementById("submit-button");

function setError(id, message) {
    document.getElementById(id).innerText = message;
}

function validateNome() {
    if(!namePattern.test(nomeInput.value)) setError("nome-error", "Il nome deve essere lungo almeno 3 caratteri.");
    else setError("nome-error", "");
}

function validateEmail() {
    if(!emailPattern.test(emailInput.value)) setError("email-error", "Inserisci un indirizzo email valido.");
    else setError("email-error", "");
}

function validateTelefono() {
    if(!phonePattern.test(telefonoInput.value)) setError("telefono-error", "Il numero di telefono deve essere lungo 10 cifre.");
    else setError("telefono-error", "");
}

function validateBudget() {
    if(isNaN(budgetInput.value) || budgetInput.value <= 0) setError("budget-error", "Il budget deve essere un numero positivo.");
    else setError("budget-error", "");
}

//if one or more services selected
function validateServizi() {
    const selectedValues = Array.from(serviziSelect.selectedOptions).map(option => option.value); // .map() to get the values of the selected option(s)
    if(selectedValues.length === 0) setError("servizi-error", "Seleziona almeno un servizio.");
    else setError("servizi-error", "");
    return selectedValues;
}

nomeInput.addEventListener("input", validateNome);
emailInput.addEventListener("input", validateEmail);
telefonoInput.addEventListener("input", validateTelefono);
budgetInput.addEventListener("input", validateBudget);
serviziSelect.addEventListener("change", validateServizi);

submitButton.addEventListener("click", function(event) {
    event.preventDefault(); //prevent form submission

    //validate all fields
    validateNome();
    validateEmail();
    validateTelefono();
    validateBudget();
    const selectedServices = validateServizi();

    //check if there are any error messages
    const errorIds = ["nome-error", "email-error", "telefono-error", "budget-error", "servizi-error"]; 
    //.some returns true if even only one of the error messages is not empty
    const hasErrors = errorIds.some(id => document.getElementById(id).innerText !== "");    

    //valid form
    if(!hasErrors) {
        document.getElementById("form-error").innerText = "";

        //open new html page
        //create query string with form data to pass them to the new page
        const queryParams = new URLSearchParams({
            nome: nomeInput.value,
            email: emailInput.value,
            telefono: telefonoInput.value,
            budget: budgetInput.value,
            servizi: selectedServices.join(",")
        }).toString();
        window.location.href = "success.html?" + queryParams;
        
    } else document.getElementById("form-error").innerText = "Impossibile inviare il form";
});