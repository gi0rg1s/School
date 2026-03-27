let d1 = new Date(document.getElementById("data1").value)
let d2 = new Date(document.getElementById("data2").value)

function calculate_differenza(){
    if((d1 - d2) < 0) return (d1 - d2) / 86400000
    else return (d2 - d1) / 86400000
}

function print_result(){
    document.getElementById("recap-container").innerHTML = `<p>Tra le due date intercorrono ${calculate_differenza()} giorni</p>`
}

document.getElementById("calcola-bottone").addEventListener("onclick", print_result)
