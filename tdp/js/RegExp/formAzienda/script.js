//validator regExp
let usernameValidator = /^[a-zA-Z0-9_]{5,20}$/;
let passwordValidator = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
let phoneValidator = /^\d{10}$/;
let sitoValidator = /^(https?:\/\/)?([\w-]+(\.[\w-]+)+)(\/[\w-]*)*\/?$/;
let businessNameValidator = /^[a-zA-Z\s]+$/;
let ambitoValidator = /^[a-zA-Z\s]+$/;


let username = document.getElementById('username');
let password = document.getElementById('password');
let phone = document.getElementById('phone');
let sito = document.getElementById('sito');
let businessName = document.getElementById('nome');
let ambito = document.getElementById('ambito');
let date = document.getElementById('data');
let button = document.getElementById('submit');

//validate function
function validateForm() {
    let isValid = true;

    validateUsername();
    validatePassword();
    validatePhone();
    validateSito();
    validateBusinessName();
    validateAmbito();
    validateDate();

    let errorIDs = ['username-error', 'password-error', 'phone-error', 'sito-error', 'business-name-error', 'ambito-error', 'data-error'];
    const hasErrors = errorIDs.some(id => document.getElementById(id).innerText !== "");    

    if (hasErrors) document.getElementById('invia-form').textContent = 'Il form contiene errori. Per favore correggili prima di inviare.';
    else document.getElementById('invia-form').textContent = '';
    
    return !hasErrors;
}

//aggiunta event listener al form
document.getElementById('submit').addEventListener('click', function(event) {
    event.preventDefault();
    if (!validateForm()) {
        event.preventDefault();
    }
}); 

//validazione username
function validateUsername() {
    if (!usernameValidator.test(username.value)) {
        document.getElementById('username-error').textContent = 'Username non valido. Deve essere tra 5 e 20 caratteri e può contenere lettere, numeri e underscore.';
    } else document.getElementById('username-error').textContent = '';
}

//password validation
function validatePassword() {
    if (!passwordValidator.test(password.value)) {
        document.getElementById('password-error').textContent = 'Password non valida. Deve essere almeno 8 caratteri e includere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale.';
    } else document.getElementById('password-error').textContent = '';
}

//phone validation
function validatePhone() {
    if (!phoneValidator.test(phone.value)) {
        document.getElementById('phone-error').textContent = 'Numero di telefono non valido. Deve essere composto da 10 cifre.';
    } else document.getElementById('phone-error').textContent = '';
}

//validating sito
function validateSito() {
    if (!sitoValidator.test(sito.value)) {
        document.getElementById('sito-error').textContent = 'URL non valido. Deve essere un URL valido (es. http://www.example.com).';
    } else document.getElementById('sito-error').textContent = '';
}

//validating business name
function validateBusinessName() {
    if (!businessNameValidator.test(businessName.value)) {
        document.getElementById('business-name-error').textContent = 'Nome dell\'azienda non valido. Deve contenere solo lettere e spazi.';
    } else document.getElementById('business-name-error').textContent = '';
}

//validating ambito
function validateAmbito() {
    if (ambito.value === "") {
        document.getElementById('ambito-error').textContent = 'Ambito non valido. Seleziona un ambito.';
    } else document.getElementById('ambito-error').textContent = '';
}

//validating date
function validateDate() {
    let dateValue = new Date(date.value);
    let today = new Date();
    if (isNaN(dateValue.getTime()) || dateValue < today) {
        document.getElementById('data-error').textContent = 'Data non valida. Deve essere una data futura.';
    } else document.getElementById('data-error').textContent = '';
}

username.addEventListener('change', validateUsername);
password.addEventListener('change', validatePassword);
phone.addEventListener('change', validatePhone);
sito.addEventListener('change', validateSito);
businessName.addEventListener('change', validateBusinessName);
ambito.addEventListener('change', validateAmbito);
date.addEventListener('change', validateDate);
button.addEventListener('click', validateForm);