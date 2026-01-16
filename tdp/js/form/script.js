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

document.getElementById("name").addEventListener("blur", validateNominativo)