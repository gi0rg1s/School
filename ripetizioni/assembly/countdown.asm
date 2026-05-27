; multi-segment executable file template.
data segment
    msg db "Insert a number -> $"
ends
stack segment
    dw   128  dup(0)
ends
code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax

    ; Stampa la stringa
    lea dx, msg
    mov ah, 09h
    int 21h

    ; Leggi input
    mov ah, 01h
    int 21h
    sub al, '0'
    mov cl, al
    mov ch, 0

    ; Memoria video, riga 1
    mov ax, 0B800h
    mov ds, ax
    mov si, 160
    mov ah, 0Eh

countdown_loop:
    mov al, cl
    add al, '0'
    mov [si], al
    mov [si+1], ah
    add si, 2

    mov byte ptr [si], ' '
    mov [si+1], ah
    add si, 2

    dec cl
    jnz countdown_loop

    mov byte ptr [si], '0'
    mov [si+1], ah

    mov ax, 4c00h
    int 21h
ends
end start