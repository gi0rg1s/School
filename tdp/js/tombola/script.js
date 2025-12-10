let board = document.getElementById("tabella")
let drawn_numbers = []
let estrai = document.getElementById("estrai") 
let reset = document.getElementById("reset")
let intervalId 
let count = 0
let finalNumber
let currentCell
let numbers = []
let ambo, terna, quaterna, cinquina, tombola
let schedine = [[], [], [], [], []]                   //array of 5 schedine

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
    for(let i = 0; i < 5; i++){                                               //check for wins in each schedina
        if(!ambo) {
            if(check_win(schedina[i], 2)) {
                ambo = true
                document.getElementById("messages").innerText = "Ambo nella schedina " + (i+1) + "!"
            }
        }
        if(!terna) {
            if(check_win(schedina[i], 3)) {
                terna = true
                document.getElementById("messages").innerText = "Terna nella schedina " + (i+1) + "!"
            }
        }
        if(!quaterna){
            if(check_win(schedina[i], 4)) {
                quaterna = true
                document.getElementById("messages").innerText = "Quaterna nella schedina " + (i+1) + "!"
            }
        }
        if(!cinquina){
            if(check_win(schedina[i], 5)) {
                cinquina = true
                document.getElementById("messages").innerText = "Cinquina nella schedina " + (i+1) + "!"
            }
        }
        if(!tombola){
            if(check_win(schedina[i], 15)) {
                tombola = true
                document.getElementById("messages").innerText = "Tombola nella schedina " + (i+1) + "!"
                return
            }
        }
    }
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

function random_schedina(schedIndex, column, min, max){
    let number
    let row
    for(let i = 0; i < 2; i++){
        number = Math.floor(Math.random() * (max - min + 1)) + min
        row = Math.floor(Math.random() * 3)
        if(!schedina[schedIndex][row * 9 + column] && !numbers.includes(number)){
            schedina[schedIndex][row * 9 + column] = number 
            numbers.push(number)
        }
        else i--
    }
}

function init_schedine(){                                                           //initialize the schedina
    const schedineContainer = document.getElementById("schedine")
    schedineContainer.innerHTML = ''
    for(let s = 0; s < 5; s++){
        numbers = []
        schedina[s] = new Array(27).fill(null)  
        for(let col = 0; col < 9; col++){
            if(col === 0) random_schedina(s, col, 1, 9)
            else if(col === 8) random_schedina(s, col, 80, 90)
            else random_schedina(s, col, col * 10, col * 10 + 9)
        }
        const schedinaDiv = document.createElement("div")
        schedinaDiv.className = "schedina-div"
        for(let r = 0; r < 3; r++){
            const rowDiv = document.createElement("div")
            rowDiv.className = "schedina-row"
            for(let c = 0; c < 9; c++){
                const cellDiv = document.createElement("div")
                cellDiv.className = "schedina-cell"
                const number = schedina[s][r * 9 + c]
                cellDiv.textContent = number !== null ? number : ''
                rowDiv.appendChild(cellDiv)
            }
            schedinaDiv.appendChild(rowDiv)
        }
        schedineContainer.appendChild(schedinaDiv)
    }
}

function check_win(schedina, n){                                               //check if there is an ambo/terna/quaterna/cinquina or a tombola
    let count = 0
    for(let i = 0; i < drawn_numbers.length; i++){
        for(let j = 0; j < schedina.length; j++){
            if(drawn_numbers[i] === schedina[j]){
                count++
                if(count === n) return true
            }
        }
    }
    return false
}


estrai.addEventListener('click', print_animation)
reset.addEventListener('click', reset_table) 
init_table()
init_schedine()