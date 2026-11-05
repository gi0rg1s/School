  
org 100h
jmp start

    num1 dw 12345
    num2 db 5 dup(0)
 
 start:
    mov bx, 10
    mov ax, num1
    mov dx, 0
    xor cx, cx
    lea si, num2     ;carico in SI l'offset del primo elemento di num2 
    CALL printProcedure
printProcedure PROC        
    saveDigit:
        div bx
        add dx, 30h
        mov [si], dx
        ;add [si], 30h  --> l'esecuzione si fa in piu' cicli di clock perche' 
                            ;deve accedere alla memoria RAM 
        mov dx, 0 
        inc si
        cmp ax, 0
        jne saveDigit
        
        
        lea cx, num2
    printDigit:
        dec si ;riporto il contatore all'ultima cifra dell'array
        mov dx, [si]
        mov ah, 02
        int 21h
         
        
        cmp si, cx
        jne printDigit
printProcedure ENDP        
   
ret
