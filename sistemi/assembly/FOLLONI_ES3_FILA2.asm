
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h 

JMP start

stringa db 30 DUP (0)

start:

LEA BX, stringa

input:
MOV AH, 1          ;input
INT 21h
                   ;copio in AL quello che c'e' dove punta BX
MOV [BX], AL 
INC BX 
                   ;controllo se e' stato premuto invio
CMP AL, 13
JE stampa

JMP input

stampa: 
MOV AH, 2
MOV DL, 10         ;new line
INT 21h
MOV AH, 2
MOV DL, 13
INT 21h

LEA BX, stringa    ;faccio puntare BX al primo carattere della stringa

minuscola:
CMP [BX], 13
JE exit

MOV DL, [BX]
OR DL, 00100000b   ;mascheramento per la lettera minuscola
MOV AH, 2          ;stampa a schermo
INT 21h 
INC BX             ;punto alla lettera successiva

maiuscola: 
CMP [BX], 13
JE exit

MOV DL, [BX]
AND DL, 01011111b  ;mascheramento per la lettera maiuscola
MOV AH, 2          ;stampa a schermo
INT 21h
                   ;punto alla lettera successiva
INC BX  
JMP minuscola 

exit:

ret 



    


