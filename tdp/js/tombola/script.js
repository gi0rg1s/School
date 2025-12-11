let board = document.getElementById("tabella")
let drawn_numbers = []
let estrai = document.getElementById("estrai") 
let reset = document.getElementById("reset")
let intervalId 
let count = 0
let finalNumber
let currentCell
const ROWS = 3
const COLS = 9
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
        
        // Highlight the number on all schedine if it exists there
        for(let n = 0; n < 5; n++) {
            for(let i = 0; i < 3; i++) {
                for(let j = 0; j < 9; j++) {
                    const index = i * 9 + j;
                    const cellId = `sched${n+1}-cell-${index}`;
                    const cell = document.getElementById(cellId);
                    if(cell && schedine[n][index] === finalNumber) {
                        cell.classList.add('matched');  // Assign the matched class to turn red
                    }
                }
            }
        }
        
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

function shuffleArray(arr){
    for(let i = arr.length - 1; i > 0; i--){
        const j = Math.floor(Math.random() * (i + 1))
        ;[arr[i], arr[j]] = [arr[j], arr[i]]
    }
    return arr
}

function pickNumbersForColumn(min, max, count, used){
    const result = []
    while(result.length < count){
        const n = Math.floor(Math.random() * (max - min + 1)) + min
        if(!used.has(n)){
            used.add(n)
            result.push(n)
        }
    }
    return result.sort((a, b) => a - b)
}

function build_schedina(){
    const rows = 3
    const cols = 9
    let attempts = 0
    while(attempts < 200){
        attempts++
        const grid = new Array(rows * cols).fill(null)
        const rowCounts = [0, 0, 0]
        const colCounts = new Array(cols).fill(1)
        shuffleArray([...Array(cols).keys()]).slice(0, 6).forEach(c => colCounts[c] = 2)
        const used = new Set()
        const columnNumbers = colCounts.map((count, col) => {
            const min = col * 10 + 1
            const max = col === 8 ? 90 : (col * 10) + 10
            return pickNumbersForColumn(min, max, count, used)
        })
        let valid = true
        for(let col = 0; col < cols && valid; col++){
            const count = colCounts[col]
            const availableRows = [0, 1, 2].filter(r => rowCounts[r] < 5)
            if(availableRows.length < count){
                valid = false
                break
            }
            shuffleArray(availableRows)
            const chosenRows = availableRows.slice(0, count).sort((a, b) => a - b)
            for(let i = 0; i < count; i++){
                const row = chosenRows[i]
                const index = row * cols + col
                grid[index] = columnNumbers[col][i]
                rowCounts[row]++
            }
        }
        if(valid && rowCounts.every(c => c === 5)) return grid
    }
    throw new Error("Impossibile generare una schedina valida")
}

function init_schedine(){                                                           
    // Initialize and display all 5 schedine (tombola cards)
    // Each schedina has 3 rows x 9 columns = 27 cells
    // Each column now has 1 or 2 numbers, and every row has exactly 5 numbers

    document.getElementById("schedine").innerHTML = ""

    for(let n = 0; n < 5; n++){  
        schedine[n] = build_schedina()
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
    }
}

function check_win(schedina, n){                                //check if there are n matching numbers between drawn and schedina
    let count = 0
    // Loop through each number in the schedina
    for(let j = 0; j < ROWS; j++){
        for(let k = 0; k < COLS; k++){
            if(schedina[j][k] !== null && schedina[j][k] !== undefined && drawn_numbers.includes(schedina[j][k])){
                count++
                if(count === n) return true  // Found enough matches!
            }
        }
        count = 0
    }
    return false
}


estrai.addEventListener('click', print_animation)
reset.addEventListener('click', reset_table) 
init_table()
init_schedine()