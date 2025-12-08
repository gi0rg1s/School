let board = document.getElementById("tabella")
let drawn_numbers = []
let estrai = document.getElementById("estrai") 
let reset = document.getElementById("reset")
let intervalId; 
let count = 0;
let finalNumber;
let currentCell;

function init_table(){
    board.innerHTML = ''
    for(let i = 1; i <= 90; i++){
        const cell = document.createElement("div")
        cell.className = "cella"
        cell.textContent = i
        cell.id = `cell-${i}` 
        board.appendChild(cell)
    }
}

function generate_random_number(){
    if (drawn_numbers.length >= 90) {
        alert("Tutti i numeri sono stati estratti");
        return null
    }
    let n
    do {
        n = Math.floor(Math.random() * 90) + 1
    } while (drawn_numbers.includes(n))
    return n;
}

function print_animation(){
    if (drawn_numbers.length >= 90) {
        alert("Tutti i numeri sono stati estratti");
        return;
    }
    count = 0;  
    intervalId = setInterval(interval_function, 100); 
}

function interval_function(){
    let tempN = Math.floor(Math.random() * 90) + 1;
    document.getElementById("animation").innerText = tempN;
    count++;
    
    if (count >= 15) {
        clearInterval(intervalId);
        
        finalNumber = generate_random_number();
        if (finalNumber === null) return;
        
        document.getElementById("animation").innerText = finalNumber;
        drawn_numbers.push(finalNumber);
        
        currentCell = document.getElementById(`cell-${finalNumber}`);
        currentCell.className = "cella-estratta";
        
        setTimeout(time_out, 1500);  
    }
}

function time_out(){
    currentCell.className = "cella cella-drawn";
}

function reset_table(){
    drawn_numbers = []  
    init_table()
    document.getElementById("animation").innerText = ""
}

function random_schedina(min, max){
    let numbers = []
    let number
    let row
    for(let i = 0; i < 2; i++){
        number = Math.floor(Math.random() * (max - min + 1)) + min
        row = Math.floor(Math.random() * 2)
        if(!schedina[row] && !numbers.includes(number)){
            schedina[row] = number 
            numbers.push(number)
        }
        else i--
    }
}

function init_schedine(){
    for(let n = 0; t < 5; t++){
        const schedina = document.createElement("div")
        schedina.className = `schedina${n+1}`
        for(let i = 1; i <= 3; i++){
            for(let j = 1; j <= 9; j++){
                const cell = document.createElement("div")
                cell.className = `cella-${i}${j}`
                cell.textContent = ""
                cell.id = `tabella-${i}-cell-${j}` 
                document.getElementById(`schedina-${n+1}`).appendChild(cell)
            }
        }
        for(let k = 0; k < 15; k++) random_schedina(n*10+1, (n*10)+10)
    }
}

estrai.addEventListener('click', print_animation)
reset.addEventListener('click', reset_table) 
init_table()