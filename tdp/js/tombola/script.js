function table(){
    let tbl = '<table><tr>'
    for(let i = 0; i < 90; i++){
        if(i % 10 == 0 && i != 0) tbl += `</tr><tr>`
        tbl += `<td>${i+1}</td>`
    }
    tbl += `</table>`
    document.getElementById("tabella").innerHTML = tbl
}