#!/usr/bin/env python3
# NOTA: si raccomanda di usare questo template anche se non lo si capisce completamente.

import sys

# decommenta le due righe seguenti se vuoi leggere/scrivere da file
sys.stdin = open('investitutto_input_2.txt')
sys.stdout = open('output.txt', 'w')

T = int(input().strip())
for test in range(1, T+1):
    input()
    N = int(input().strip())

    V = list(map(int, input().strip().split()))

    G = list(map(int, input().strip().split()))

    ris = 0
    

    # INSERISCI IL TUO CODICE QUI

    money = []
    for i in range(0, N):
        money.append(V[i] + (G[i] * (N - i)))
    ris = max(money)


    print("Case #%d: " % test, end='')
    print(ris)

sys.stdout.close()
