;lavoro di gruppo--> Folloni, Sida, Bocchi, Acquah
data segment
    input db 16,?,16 dup(0)             
    contaOp dw 1                          ; Contatore delle operazioni eseguite
    number1 dw ?                          ; Primo operando
    number2 dw ?                          ; Secondo operando
    operator dw ?                         ; Operatore matematico (+, -, *, /, %)
    result dw ?                           ; Risultato dell'operazione
    impossible db 10, 13, "impossibile eseguire una divisione per 0!", 10, 13, "$" 
    fine db 10,13, "programma chiuso arrvivederci :) ",10,13, "$" 
ends
stack segment
    dw   128  dup(0)                      
ends
code segment
start:
    mov ax, data                         
    mov ds, ax                           
    
calculator:
    call contaOperazione                 ; Incrementa e stampa il contatore delle operazioni eseguite

    LEA DX, input                        ; Carica l'indirizzo di input in DX
    MOV AH, 0Ah                          ; Richiede l'input da tastiera
    INT 21h                              ; Interruzione DOS per input

    lea bx, input                        ; Carica l'indirizzo di input in BX
    add bx, 2                            ; Salta il primo byte che contiene la lunghezza dell'input
    call getInt                          ; Converte il primo numero da stringa a valore numerico
    mov number1, ax                      ; Salva il primo operando in number1

    cmp [bx], ' '                        ; Controlla se c'e' uno spazio dopo il numero
    jne continueOp                       ; Se non c'e' spazio, salta
    inc bx                               ; Altrimenti, incrementa BX
               
    continueOp: 
    mov ax, [bx]                         ; Legge il segno operativo
    mov operator, dx                     ; Salva il segno operativo in operator

    secondInt:
    inc bx                               ; Incrementa BX per leggere il secondo numero
    cmp [bx], ' '                        ; Controlla se c'è uno spazio
    je secondInt                         ; Salta gli spazi

    call getInt                          ; Converte il secondo numero da stringa a valore numerico
    mov number2, ax                      ; Salva il secondo operando in number2

    call getOperator                     ; Esegue l'operazione richiesta
    call printResult                     ; Stampa il risultato dell'operazione

    mov dl, 10                           ; Aggiunge una nuova linea
    mov ah, 2
    int 21h                              ; Interruzione DOS per output
    mov dl, 13
    mov ah, 2
    int 21h                              ; Ritorno a capo
    jmp calculator                       ; Ricomincia il calcolatore

    exitCalc:                            ; Etichetta per uscire dal programma 
    lea dx, fine
    mov ah, 9
    int 21h                           
    mov ax, 4c00h                        ; Termina il programma e ritorna al DOS
    int 21h                              ; Interruzione DOS per terminare

;procedura per stampare a schermo l'indice dell'operazione
contaOperazione proc
    xor ax, ax                           
    xor bx, bx                           
    mov ax, contaOp                      ; set dei registri
    mov dx, ax                           
    inc dx                               ; Incrementa di 1
    mov contaOp, dx                      ; Salva il nuovo valore in contaOp
    mov bx, 10                           ; Imposta il divisore per la conversione in ASCII
    xor cx, cx                           

    count:
    inc cx                               ; Incrementa CX (contatore delle cifre)
    xor dx, dx                           ; Azzera DX
    div bx                               ; Divide AX per 10 per ottenere la cifra meno significativa
    push dx                              ; Salva la cifra nello stack
    cmp ax, 0                            ; Controlla se il quoziente è 0
    jne count                            ; Se non e' 0, continua a dividere

    print:
    pop dx                               ; Recupera la cifra dallo stack
    add dx, 30h                          ; Converte la cifra in ASCII
    mov ah, 2
    int 21h                              ; Stampa la cifra
    loop print                           ; Continua a stampare finché ci sono cifre

    mov dl, ']'                          ; Stampa ']' a schermo
    mov ah, 2
    int 21h 
                                 
    ret                                  ; Ritorna al chiamante
contaOperazione endp

;procedura che converte un numero in formato stringa in un valore numerico
getInt proc 
    mov al, [bx]                         ; Carica il valore di input in AL
    xor ah, ah                           ; Azzera AH
    cmp ax, 'X'                          ; Controlla se l'input e' 'X' per uscire
    je exitCalc
    cmp ax, 'x'
    je exitCalc                          ; Se e' X o x, termina il programma
    sub ax, 30h                          ; Converte il carattere ASCII in un numero

getNum:
    inc bx                               ; Incrementa BX per leggere il prossimo carattere
    mov dl, [bx]                         ; Carica il prossimo carattere
    cmp dx, 30h                          ; Controlla se e' una cifra
    jb got                               ; Se è minore di '0', esce dal ciclo
    cmp dx, 39h                          ; Controlla se e' maggiore di '9'
    ja got                               ; Se è maggiore di '9', esce dal ciclo
    sub dx, 30h                          ; Converte il carattere ASCII in un numero

    mov cx, 10                           ; Imposta CX a 10
    mul cl                               ; Moltiplica AX per 10
    add ax, dx                           ; Aggiunge il numero convertito
    jmp getNum                           ; Continua a leggere il prossimo carattere

got:
    ret                                  ; Ritorna al chiamante
getInt endp

;procedura che esegue l'operazione specificata dall'operatore
getOperator proc 
    xor ax, ax                          
    xor bx, bx                           
    xor dx, dx                           
    mov ax, number1                             ;set dei registri
    mov bx, number2                      

    cmp operator, '+'                          
    je addizione                         
    cmp operator, '-'                          
    je sottrazione                       
    cmp operator, '/'                          ;controllo l'operazione da eseguire
    je divisione
    cmp operator, '\'
    je divisione                         
    cmp operator, '%'
    je percentuale                       
    cmp operator, '*'
    je moltiplicazione
    cmp operator ,'^'
    je potenza                             
    cmp operator ,'&'
    je and_operation
    cmp operator,'|' 
    je or_operation
    cmp operator , '!'
    je fatoriale   
    
                           

    moltiplicazione:
    mul bx                               ; Moltiplica AX per BX
    jmp exit

    percentuale:
    mov cx, 100
    mul bx                               ; Calcola la percentuale
    div cx
    jmp exit

    potenza:
    cmp bx, 1
    je exit
    cmp bx, 0
    jne potenza2
    
    mov ax, 1
    jmp exit
    
    potenza2:
    dec bx
    mov cx, bx                           ; Salva BX in CX per il conteggio
    mov bx, ax
    
    sloop:
    mul bx                               ; Moltiplica per se' stesso 
    loop sloop                           ; Continua finche' CX non diventa 0
    jmp exit

    divisione:     
    cmp bx, 0
    je imp
    
    mov dx, 0
    mov ax, number1
    idiv number2                         ; ax = (dx ax) / num2.
    jmp exit

    sottrazione:
    sub ax, bx                           ; Sottrae BX da AX
    jmp exit

    addizione:
    add ax, bx                           ; Somma BX ad AX
    jmp exit 
    
    and_operation:
    and ax, bx
    jmp exit 
    
    or_operation:
    or ax,bx
    jmp exit 
    
   fatoriale:
    mov ax, 1             ; Inizializza AX a 1 e'
    mov cx, number1       ; Carica il numero di cui calcolare il fattoriale in CX
    cmp cx, 0             ; Controlla se il numero  0
    je exit               ; Se e' 0, il fattoriale e' 1 
        
fatoriale_loop:
    mul cx                ; Moltiplica AX per CX
    loop fatoriale_loop   ; Decrementa CX e continua il ciclo finché CX > 0

    mov result, ax
    jmp exit        ; Salva il risultato del fattoriale in `result`

    imp:
    lea dx, impossible                   
    mov ah, 9
    int 21h                              ; Mostra il messaggio a schermo
    jmp calculator                       ; Torna al calcolatore

    exit:
    mov result, ax                       ; Salva il risultato in result
    ret                                  ; Ritorna al chiamante
getOperator endp

;procedura che stampa il risultato a schermo
printResult proc 
    mov dl, 10                           ; New line
    mov ah, 2
    int 21h
    mov dl, 13
    mov ah, 2
    int 21h    

    mov dl, '='                          ; Stampa '=' a schermo
    mov ah, 2
    int 21h
    xor ax, ax                           
    xor bx, bx                           
    xor cx, cx                           
    mov ax, result                       ; Carica il risultato in AX
    mov bx, 10                           ; Imposta il divisore per la conversione in ASCII

    getResult:
        inc cx                           ; Incrementa CX per contare le cifre
        xor dx, dx                       ; Azzera DX
        div bx                           ; Divide il risultato per 10
        push dx                          ; Salva il resto nello stack
        cmp ax, 0                        ; Controlla se il quoziente e' 0
        jne getResult                    ; Continua finché non e' 0

    outputResult:
        pop dx                           ; Recupera il resto dallo stack
        add dx, 30h                      ; Converte in ASCII
        mov ah, 2                        ; Stampa il numero a schermo
        int 21h
    loop outputResult
    ret 
printResult endp

ends

end start 