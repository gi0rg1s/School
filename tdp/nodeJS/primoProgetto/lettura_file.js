const fs = require('fs')

//LETTURA FILE BLOCCANTE (SINCRONA)
console.log('--- TEST BLOCCANTE ---')
console.log('start')

let file = fs.readFileSync('ciao.txt', 'utf8')
console.log(file)

console.log('End')


//LETTURA FILE NON BLOCCANTE (ASINCRONA)
console.log('\n--- TEST NON BLOCCANTE ---')
console.log('start')

fs.readFile('ciao.txt', 'utf8', function(errore, data){
    if(errore){
        console.log(errore)
        process.exit(1)
    } else {
        console.log(data)
    }
})

console.log('End')
