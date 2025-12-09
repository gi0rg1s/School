let board = document.getElementById("tabella")
let drawn_numbers = []
let estrai = document.getElementById("estrai") 
let reset = document.getElementById("reset")
let intervalId 
let count = 0
let finalNumber
let currentCell
let numbers = []

function init_table(){                                              //create the table 
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
    if (drawn_numbers.length >= 90) {                                           //extract a random number
        alert("Tutti i numeri sono stati estratti");
        return null
    }
    let n
    do {
        n = Math.floor(Math.random() * 90) + 1
    } while (drawn_numbers.includes(n))                                         //extract a new number while it is already extracted
    return n;
}

function print_animation(){
    if (drawn_numbers.length >= 90) {
        alert("Tutti i numeri sono stati estratti");
        return;
    }
    count = 0;  
    intervalId = setInterval(interval_function, 100);                           //interval for the animation
}

function interval_function(){
    let tempN = Math.floor(Math.random() * 90) + 1;
    document.getElementById("animation").innerText = tempN;
    count++;
    
    if (count >= 15) {
        clearInterval(intervalId);                                              //generate 15 different numbers for the animation
        
        finalNumber = generate_random_number();
        if (finalNumber === null) return;
        
        document.getElementById("animation").innerText = finalNumber;
        drawn_numbers.push(finalNumber);
        
        currentCell = document.getElementById(`cell-${finalNumber}`);          //change the class of the extract number to colorate it by red
        currentCell.className = "cella-estratta";
        
        setTimeout(time_out, 1500);                                             //time sleeping
    }
}

function time_out(){
    currentCell.className = "cella cella-drawn";                                //change again the class to be temporary drawn in red
}

function reset_table(){                                                         //reset the table
    drawn_numbers = []  
    init_table()
    document.getElementById("animation").innerText = ""
}

function random_schedina(column, min, max){
    let number
    let row
    for(let i = 0; i < 2; i++){
        number = Math.floor(Math.random() * (max - min + 1)) + min
        row = Math.floor(Math.random() * 2)
        if(!schedina[row][column] && !numbers.includes(number)){
            schedina[row][column] = number 
            numbers.push(number)
        }
        else i--
    }
}

function init_schedine(){                                                           //initialize the schedina
    for(let n = 0; n < 5; n++){
        const schedina = document.createElement("div")
        schedina.id = `schedina${n+1}`
        schedina.className = `schedina${n+1}`
        document.getElementById("schedine").appendChild(schedina)
        for(let i = 1; i <= 3; i++){
            for(let j = 1; j <= 9; j++){
                const cell = document.createElement("div")
                cell.className = `cella-${i}${j}`
                cell.textContent = ""
                cell.id = `tabella-${i}-cell-${j}` 
                document.getElementById(`schedina-${n+1}`).appendChild(cell)
            }
        }
        for(let k = 0; k < 9; k++) random_schedina(k, k*10+1, (k*10)+10)
        numbers = []                                                                 //clear the array whit extract numbers for each schedina
    }
}

estrai.addEventListener('click', print_animation)
reset.addEventListener('click', reset_table) 
init_table()
init_schedine()