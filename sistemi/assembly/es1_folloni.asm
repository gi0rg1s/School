.MODEL SMALL
.STACK 100H
.DATA
    msg1 DB 'Inserisci valore di x: $'
    msg2 DB 'P(x) = $'
    newline DB 10,13,'$'
    risultato_str DB 10,13,'Il risultato del polinomio e: $', 0
    risultato_msg DB '         $' 
    
    buffer DB 5, 0, 5 DUP('$')
    x_val DW ?
    risultato DW 0

.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX    
    MOV ES, AX


    LEA DX, msg1
    PUSH DX
    CALL PrintString
    CALL ReadNumber
    MOV x_val, AX

    
    XOR AX, AX

    ; Calcola P(x)=2x^4-3x^3-8x-7
    ; 2x^4
    PUSH x_val
    PUSH 4
    CALL pow
    MOV BX, 2
    CALL product
    ADD risultato, AX

    ; -3x^3
    PUSH x_val
    PUSH 3
    CALL pow
    MOV BX, 3
    CALL product
    SUB risultato, AX

    ; -8x
    MOV AX, x_val
    MOV BX, 8
    CALL product
    SUB risultato, AX

    ; -7
    SUB risultato, 7  
    
    ; ---- Converti il messaggio in maiuscolo ----
    LEA DX, risultato_str 
    PUSH DX
    CALL to_upper   
    
    LEA DX, risultato_str
    PUSH DX
    CALL PrintString

    MOV AX, risultato
    CALL ConvertToString 
    
    LEA DX, risultato_msg   
    PUSH DX
    CALL PrintString



    MOV AX, 4C00H
    INT 21H
    ret
MAIN ENDP 


; Moltiplica due numeri
product PROC
              
    mul bx           ;multiply ax and bx
    ret

product ENDP

; Calcola la potenza: x^y 
pow PROC 
    
    pop si            ;pick from the stack the return address
    pop cx            ;pick the first operator from the stack
    pop ax            ;pick the second operator from the stack
    dec cx            
    mov bx, ax        ;registers' set
    pow_loop:
        mul bx
    loop pow_loop 
    push si 
    ret

pow ENDP

; Converte una stringa in maiuscolo (in place)
to_upper proc
    pop si            ; save return address
    pop dx            ; get string address
    mov bx, dx
upper_loop:
    mov al, [bx]      ; load character from string
    cmp al, '$'       ; check for end of string
    je upper_done
    cmp al, 'a'       ; check if character is below 'a'
    jb skip_conversion
    cmp al, 'z'       ; check if character is above 'z'
    ja skip_conversion
    sub al, 20h       ; convert to uppercase
    mov [bx], al      ; store back converted character
skip_conversion:
    inc bx            ; move to next character
    jmp upper_loop
upper_done:
    push si           ; restore return address
    ret
to_upper endp

; Procedure PrintString     
PrintString PROC 
    pop si          ;pick from the stack the return adress
    pop dx          ;pick the string from stack
    mov ah, 9       ;print string
    int 21h
    push si         ;put the return adress into the stack
    ret

PrintString ENDP 

; Procedure per la conversione in stringa
ConvertToString PROC

    MOV BX, 10  ; Divisore per ottenere le cifre
    MOV DI, OFFSET risultato_msg + 2  
ConvLoop:
    XOR DX, DX
    DIV BX  
    ADD DL, '0'  
    DEC DI
    MOV [DI], DL
    TEST AX, AX
    JNZ ConvLoop  

    MOV DX, DI  
    RET
ConvertToString ENDP


; Procedure per leggere un numero intero a 8 bit e restituirlo in AX
ReadNumber PROC
    XOR CX, CX         ;regisers set
    LEA DX, buffer 

    
    MOV AH, 0Ah        ;input string
    INT 21h     
    LEA SI, buffer + 2 

convertLoop:
    LODSB 
    CMP AL, 0Dh            ;control if there's another number or not
    JE finishConversion 
    SUB AL, '0'            ;sub 30h to have the absolute value
    MOV BH,AL
    MOV AX,CX              
    MOV BL, 10             ;put 10 in bl to the mul loop
    MUL BL                 ;multiply al and bl
    MOV BL,BH
    XOR BH,BH
    ADD AX,BX    
    MOV CX,AX
    JMP convertLoop 

finishConversion: 
    MOV AX,CX
    RET
ReadNumber ENDP

