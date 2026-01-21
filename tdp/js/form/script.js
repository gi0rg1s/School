function validateNominativo() {
    let nominativo = document.getElementById("name").value;
    for (let i = 0; i < nominativo.length; i++) {
        let char = nominativo.charAt(i);
        if(char !== ' ' && char.toLowerCase() === char.toUpperCase()) {
            document.getElementById("errNome").innerText = "Errore: il nominativo deve contenere solo lettere!";
            return false;
        }
    }
    document.getElementById("errNome").innerText = "";
    return true;
}

function validateAge(){
    let age = document.getElementById("age").value;
    if(age < 18){
        document.getElementById("errAge").innerText = "Errore: devi essere maggiorenne per registrarti!";
        return false;
    }
    document.getElementById("errAge").innerText = "";
    return true;
}

function validatePassword(){
    let password = document.getElementById("password").value;
    let number = false;
    let upper = false;
    if(password.length < 8){
        document.getElementById("errPassword").innerText = "Errore: la password deve essere lunga almeno 8 caratteri!";
        return false;
    }
    for(let i = 0; i < password.length; i++){
        let char = password.charAt(i);
        if(!isNaN(char * 1)){
            number = true;
        }
        if(char.toUpperCase() === char && char.toLowerCase() !== char){
            upper = true;
        }
    }
    if(!number || !upper){
        document.getElementById("errPassword").innerText = "Errore: la password deve contenere almeno un numero e una lettera maiuscola!";
        return false;
    }
    document.getElementById("errPassword").innerText = "";
    return true;
}

function validateForm(event){
    event.preventDefault();
    if(!(validateNominativo() && validateAge() && validatePassword())){
        document.getElementById("submit").innerText = "Errore durante la compilazione del form!";
        return false;
    } 
    open("goodMsg.html", "_self");
}

document.getElementById("name").addEventListener("blur", validateNominativo)
document.getElementById("age").addEventListener("blur", validateAge)
document.getElementById("password").addEventListener("blur", validatePassword)
document.getElementById("form").addEventListener("submit", validateForm)