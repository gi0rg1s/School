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
            if(check_win(schedine[i], 2)) {
                ambo = true
                document.getElementById("messages").innerText = "Ambo nella schedina " + (i+1) + "!"
            }
        }
        if(!terna) {
            if(check_win(schedine[i], 3)) {
                terna = true
                document.getElementById("messages").innerText = "Terna nella schedina " + (i+1) + "!"
            }
        }
        if(!quaterna){
            if(check_win(schedine[i], 4)) {
                quaterna = true
                document.getElementById("messages").innerText = "Quaterna nella schedina " + (i+1) + "!"
            }
        }
        if(!cinquina){
            if(check_win(schedine[i], 5)) {
                cinquina = true
                document.getElementById("messages").innerText = "Cinquina nella schedina " + (i+1) + "!"
            }
        }
        if(!tombola){
            if(check_win(schedine[i], 15)) {
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
        
        // Highlight the number on the main board
        currentCell = document.getElementById(`cell-${finalNumber}`);          
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
    for(let i = 0; i < 2; i++){  // 2 numbers per row
        number = Math.floor(Math.random() * (max - min + 1)) + min  // Random number in range
        row = Math.floor(Math.random() * 3)  // Random row (0, 1, or 2)
        
        const index = row * 9 + column
        if(!schedine[schedIndex][index] && !numbers.includes(number)){
            schedine[schedIndex][index] = number  // Store the number
            numbers.push(number)  // Track used numbers to avoid duplicates
        }
        else i--  // If cell taken or number duplicate, try again
    }
}

function init_schedine(){                                                           
    // Initialize and display all 5 schedine (tombola cards)
    // Each schedina has 3 rows x 9 columns = 27 cells
    // Each column contains 2 numbers from its decade (col 0: 1-10, col 1: 11-20, etc.)
    
    for(let n = 0; n < 5; n++){  
        schedine[n] = new Array(27).fill(null)
        
        // Generate random numbers for each column schedina
        for(let k = 0; k < 9; k++) random_schedina(n, k, k*10+1, (k*10)+10)
        
        // Create the html container for the schedine
        const schedDiv = document.createElement("div")
        schedDiv.id = `schedina-${n+1}`
        schedDiv.className = `schedina`
        document.getElementById("schedine").appendChild(schedDiv)
        
        // STEP 4: Create 27 cell divs and populate with numbers
        for(let i = 0; i < 3; i++){  // 3 rows
            for(let j = 0; j < 9; j++){  // 9 columns
                const cell = document.createElement("div")
                cell.className = `cella-schedina`
                const index = i * 9 + j
                const numValue = schedine[n][index]
                cell.textContent = numValue !== null ? numValue : ""
                cell.id = `sched${n+1}-cell-${index}` 
                schedDiv.appendChild(cell)  // Add cell to schedina div
            }
        }
        numbers = []                                                                 //clear the array whit extract numbers for each schedina
    }
}

function check_win(schedina, n){                                               //check if there are n matching numbers between drawn and schedina
    let count = 0
    // Loop through each number in the schedina
    for(let j = 0; j < schedina.length; j++){
        // If this schedina cell has a number and it's in drawn_numbers, count it
        if(schedina[j] !== null && schedina[j] !== undefined && drawn_numbers.includes(schedina[j])){
            count++
            if(count === n) return true  // Found enough matches!
        }
    }
    return false
}


estrai.addEventListener('click', print_animation)
reset.addEventListener('click', reset_table) 
init_table()
init_schedine()