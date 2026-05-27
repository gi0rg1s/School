#include <stdio.h>
#include <limits.h>
#include <float.h>
#include <stdint.h>
#include <string.h>

void stampa_float_bits(float f);
void stampa_bits(unsigned char byte);


int main(){
	printf("sizeof(char) = %llu\n", sizeof(char));						//1 byte
	printf("CHAR_MIN = %d\nCHAR_MAX = %d\n", CHAR_MIN, CHAR_MAX);
	printf("UCHAR_MIN = %u\nUCHAR_MAX = %u\n", 0, UCHAR_MAX);

    printf("sizeof(short) = %llu\n", sizeof(short));					
	printf("SHRT_MIN = %d\nSHRT_MAX = %d\n", SHRT_MIN, SHRT_MAX);
	printf("USHRT_MIN = %u\nUSHRT_MAX = %u\n", 0, USHRT_MAX);

	printf("sizeof(int) = %llu\n", sizeof(int));
	printf("INT_MIN = %d\nINT_MAX = %d\n", INT_MIN, INT_MAX);
	printf("UINT_MIN = %u\nUINT_MAX = %u\n", 0, UINT_MAX);

	printf("sizeof(long) = %llu\n", sizeof(long));
	printf("LONG_MIN = %ld\nLONG_MAX = %ld\n", LONG_MIN, LONG_MAX);
	printf("ULONG_MIN = %u\nULONG_MAX = %lu\n", 0, ULONG_MAX);

	printf("sizeof(long long) = %llu\n", sizeof(long long));
	printf("LLONG_MIN = %lld\nLLONG_MAX = %lld\n", LLONG_MIN, LLONG_MAX);
	printf("ULLONG_MIN = %u\nULLONG_MAX = %llu\n", 0, ULLONG_MAX);

    	printf("sizeof(float) = %llu\n", sizeof(float));
	printf("FLT_MIN = %e\nFLT_MAX = %e\n", FLT_MIN, FLT_MAX);

   	printf("sizeof(double) = %llu\n", sizeof(double));
	printf("DBL_MIN = %e\nDBL_MAX = %e\n", DBL_MIN, DBL_MAX);

   	printf("sizeof(long double) = %llu\n", sizeof(long double));
	printf("LDBL_MIN = %Le\nLDBL_MAX = %Le\n", LDBL_MIN, LDBL_MAX);

	//overflow per unsigned
	unsigned char x = 255;
	x = x + 1;
	printf("%d\n", x);

	//overflow per signed
	char x2 = 128;
	x2++;
	printf("%d\n", x);

	//float |_| |_|_|_|_|_|_|_|_| |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|...
	//		+/-       exp 					mantissa

	float valori[] = {0.5f, -1.0f, 1.5f, 0.25f};
    int n = sizeof(valori) / sizeof(valori[0]);

    for (int i = 0; i < n; i++) {
        printf("\n=== %.2f ===\n", valori[i]);
        stampa_float_bits(valori[i]);
    }
	return 0;
}
void stampa_bits(unsigned char byte) {
    for (int i = 7; i >= 0; i--)
        printf("%d", (byte >> i) & 1);
}

void stampa_float_bits(float f) {
    uint32_t n;
    memcpy(&n, &f, 4); // copia i 4 byte del float senza reinterpretarli

    printf("Bits grezzi: ");
    for (int i = 31; i >= 0; i--)
        printf("%d", (n >> i) & 1);
    printf("\n");

    // estrazione manuale delle 3 parti
    int segno    = (n >> 31) & 0x1;
    int esponente = (n >> 23) & 0xFF;
    int mantissa  = n & 0x7FFFFF;

    printf("[ %d | %08d | ", segno, 0); 
    printf("Segno: %d | Esponente raw: %d (%d reale) | Mantissa: %d\n",
           segno, esponente, esponente - 127, mantissa);
}