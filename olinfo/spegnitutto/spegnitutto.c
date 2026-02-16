// NOTA: si raccomanda di usare questo template anche se non lo si capisce completamente.

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAXN 100000

int N, T, i, ris, test;
int A[MAXN];

int main() {
    // decommenta le due righe seguenti se vuoi leggere/scrivere da file
    freopen("spegnitutto_input_4.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    assert(1 == scanf("%d", &T));
    for (test = 1; test <= T; ++test) {
        assert(1 == scanf("%d", &N));

        for (i = 0; i < N; ++i)
            assert(1 == scanf("%d", &A[i]));

        ris = 0;


        // INSERISCI IL TUO CODICE QUI

        int howManyCouples = 0;
        int howManyOnes = 0;
        int i = 0;

        for(i = 0; i < N ; i ++){
            if(A[i] == 1){
                howManyOnes++;
                if(i < N - 1 && A[i] == A[i + 1]){
                    howManyCouples++;
                    howManyOnes++;
                    i++;
                }
            }
        }

        if(N == howManyOnes){
            if(N % 2 == 0) ris = N / 2;
            else ris = (N / 2) + 1;
        }

        else if (howManyCouples > 0){
            if ((howManyOnes % 2 == 0) && (howManyOnes / 2 == howManyCouples)) ris = howManyCouples;
            else ris = howManyCouples + (howManyOnes - (howManyCouples * 2));
        }

        else ris = howManyOnes;


        printf("Case #%d: ", test);
        printf("%d\n", ris);
    }

    return 0;
}
