// NOTA: si raccomanda di usare questo template anche se non lo si capisce completamente.

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAXN 100000

int reverse_compare(const void *a, const void *b);
void reverse_sort(int *arr, size_t n);


int N, T, i, ris, test;
int C[MAXN], P[MAXN];

int main() {
    // decommenta le due righe seguenti se vuoi leggere/scrivere da file
    freopen("abbassatutto_input_3.txt", "r", stdin);
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

            int temp[MAXN];
            for (i = 0; i < N; i++) temp[i] = C[i];
            
            reverse_sort(temp, N);
            int sum = 0;
            if(temp[0] == temp[N - 1]){
                for(int j = 0; j < N; j++) sum += P[j];
                ris = sum * C[0];
            }

            else{
                reverse_sort(P, N);
                for (i = 0; i < N - 1; i++) {
                    if(C[i] < C[i + 1]){
                        P[i]  -= (P[i] - P[i + 1]) - 1;
                        ris += (P[i] - P[i + 1] - 1) * C[i];
                    }
                    else{
                        ris += P[i] * C[i];
                        P[i] = 0;
                    }
                }
                ris += P[N - 1] * C[N - 1];
            }

        printf("Case #%d: ", test);
        printf("%d\n", ris);
    }

    return 0;
}

int reverse_compare(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    return (x < y) - (x > y);  // opposite of normal compare
}

void reverse_sort(int *arr, size_t n) {
    qsort(arr, n, sizeof(int), reverse_compare);
}
