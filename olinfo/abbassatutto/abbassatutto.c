// NOTA: si raccomanda di usare questo template anche se non lo si capisce completamente.

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAXN 100000

int N, T, i, ris, test;
int C[MAXN], P[MAXN];

int main() {
    // decommenta le due righe seguenti se vuoi leggere/scrivere da file
    freopen("abbassatutto_input_1.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    assert(1 == scanf("%d", &T));
    for (test = 1; test <= T; ++test) {
        assert(1 == scanf("%d", &N));

        for (i = 0; i < N; ++i)
            assert(1 == scanf("%d", &P[i]));

        for (i = 0; i < N; ++i)
            assert(1 == scanf("%d", &C[i]));

        ris = 0;


        // INSERISCI IL TUO CODICE QUI

        for(int i = 0; i < N; i++){
            ris += C[i] * P[i];
        }


        printf("Case #%d: ", test);
        printf("%d\n", ris);
    }

    return 0;
}
