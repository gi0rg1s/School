#!/usr/bin/env python3
# NOTA: si raccomanda di usare questo template anche se non lo si capisce completamente.

import sys

# decommenta le due righe seguenti se vuoi leggere/scrivere da file
sys.stdin = open('abbassatutto_input_1.txt')
sys.stdout = open('output.txt', 'w')

T = int(input().strip())
for test in range(1, T+1):
    input()
    N = int(input().strip())

    P = list(map(int, input().strip().split()))

    C = list(map(int, input().strip().split()))

    ris = 0


    # INSERISCI IL TUO CODICE QUI

    temp = C.sort()
    if(C[0] == C[N - 1]):
        ris = sum(P) * C[0]

    else:
        for i in range(0, N - 1):
            P.sort()
            P.reverse()
            if(C[i] < C[i + 1]):
                P[i]  -= P[i] - P[i + 1] + 1
                ris += (P[i] - P[i + 1] + 1) * C[i]
            else:
                ris += P[i] * C[i]
                P[i] = 0

    print("Case #%d: " % test, end='')
    print(ris)

sys.stdout.close()
