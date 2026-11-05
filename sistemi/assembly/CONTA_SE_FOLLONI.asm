
; Folloni Giorgia           3C INF              05/12/2024              FILA1
;Scrivi una sequenza di 10 numeri consecutivi in memoria. Conta quanti di questi numeri sono dispari 
;(i dispari hanno LSB a 1)
;Stampa il conteggio.   

org 100h

JMP start

numeri db 32, 14, 64, 33, 21, 44, 28, 43, 10, 76, 0
pari1 db "pari;", 0
dispari1 db 10, 13, "dispari", 0

start: 

MOV BX, OFFSET numeri
MOV CX, 10
      
sloop:   
MOV AX, [BX] 
AND AX, 00000001b
JZ dispari 

INC DH
INC BX
INC CX
JMP sloop 

dispari:
INC DL
INC BX

loop sloop

MOV BX, OFFSET pari1
XOR CX, CX
MOV CX, 100

stampap:
MOV DL, [BX]
MOV AX, 2
INT 21h

CMP BX, 0

INC BX

JE np
loop stampap

np:
MOV DL, DH
MOV AH, 2
INT 21h 


MOV BX, OFFSET dispari1
XOR CX, CX
MOV CX, 100

stampad:
MOV DL, [BX]
MOV AX, 2
INT 21h

CMP BX, 0

INC BX

JE nd 
loop stampad

nd: 

MOV BL, 40h
SUB BL, DH
MOV DL, BL
MOV AH, 2
INT 21h

ret