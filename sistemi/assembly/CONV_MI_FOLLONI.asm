;Folloni Giorgia                3C INF                          08/12/2024          FILA1
;Leggi una sequenza di caratteri da tastiera (terminata da INVIO) e forzala tutta in minuscolo 
;senza usare ADDIZIONI    

org 100h 
 
MOV DI, 0200h      ;imposto DI all'indirizzo di memoria 0200h
 
start: 

MOV AH, 1          ;input da tastiera
INT 21h

MOV [DI], AL       ;copio quello che c'e' nell'indirizzo in cui punta DI in AL
INC DI
 
CMP AL, 13         ;controllo se e' stato premuto invio
JE continue 
JMP start
          
continue: 

MOV DI, 0200h      ;imposto DI all'indirizzo di memoria 0200h

MOV AH, 2          ;new line
MOV DL, 10
INT 21h 
MOV AH, 2
MOV DL, 13
INT 21h

stampa:

CMP [DI], 13       ;controllo se quello che c'e' nell'indirizzo di memoria in cui punta DI e' invio
JE exit

MOV BL, [DI]
OR BL, 00100000b   ;mascheramento

MOV AH, 2
MOV DL, BL         ;stampa a schermo
INT 21h

INC DI             ;incremento l'indirizzo a cui punta DI

JMP stampa

exit:

ret