; string exercise
data segment
    buffer   db 255, 0, 255 dup(0) 
    nWords   db 0
    nLetters db 0
    inWord   db 0
    
    s1 db 10, 13, "number of letters: $" 
    s2 db 10, 13, "number of words: $"
    s3 db 10, 13, "average letters per word: $"
ends
stack segment
    dw 128 dup(0)
ends
code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax
    
    ; read input string
    mov ah, 0Ah  
    lea dx, buffer
    int 21h    
    
    lea si, [buffer+2]      ; skip metadata bytes
    mov bl, [buffer+1]      ; read string length
    mov bh, 0   
   
sloop: 
    cmp bx, 0               ; finished reading the string?
    je fine   
       
    mov al, [si]            ; load current character into AL
       
    ; check if it's a letter
    cmp al, 'A'
    jb notAlpha
    cmp al, 'Z'
    jbe isAlpha
    cmp al, 'a'
    jb notAlpha
    cmp al, 'z'
    jbe isAlpha
    jmp notAlpha

isAlpha:
    inc nLetters
    jmp nextC  

notAlpha:
    cmp al, ' '                 ; is it a space?
    jne nextC
    inc nWords                  ; yes -> word finished, count it
       
nextC:
    inc si
    dec bx
    jmp sloop 
   
fine: 
    ; check if last word was not followed by a space
    cmp nLetters, 0
    je exit
    inc nWords

printLetters:
    mov ah, 09h
    lea dx, s1
    int 21h
    xor ax, ax
    mov al, [nLetters]
    push ax
    call printNumber

printWords:
    mov ah, 09h
    lea dx, s2
    int 21h
    xor ax, ax
    mov al, [nWords]
    push ax
    call printNumber

printAverage:
    mov ah, 09h
    lea dx, s3
    int 21h
    
    xor ax, ax
    mov al, [nLetters]
    xor bx, bx
    mov bl, [nWords]
    
    cmp bl, 0               ; avoid division by zero!
    je exit
    
    xor dx, dx
    div bx                  ; AL = quotient (average)
    push ax
    call printNumber

exit: 
    mov ax, 4c00h
    int 21h   

; ---------------------------------------------------
; printNumber: prints a number passed on the stack
; usage: push ax / call printNumber
; uses: AX, BX, CX, DX
; ---------------------------------------------------
printNumber proc
    pop cx          ; salva return address in CX
    pop ax          ; prendi l'argomento
    xor ah, ah      ; tieni solo il byte basso
    push cx         ; rimetti return address

    mov bx, 10
    xor cx, cx
getNum:
    inc cx
    xor dx, dx
    div bx
    push dx
    cmp ax, 0
    jne getNum
outputNumber:
    pop dx
    add dx, '0'
    mov ah, 02h
    int 21h
    loop outputNumber
    ret             ; ret senza 2 perché l'argomento è già stato poppato
printNumber endp

ends
end start