#include <stdio.h>

int main(void) {
    unsigned char max_uchar = (unsigned char)0xFFu;
    signed char max_schar = (signed char)(max_uchar >> 1);
    signed char min_schar = (signed char)(-max_schar - 1);

    unsigned short max_ushort = (unsigned short)0xFFFFu;
    short max_short = (short)(max_ushort >> 1);
    short min_short = (short)(-max_short - 1);

    unsigned int max_uint = 0xFFFFFFFFu;
    int max_int = (int)(max_uint >> 1);
    int min_int = -max_int - 1;

    unsigned long max_ulong = 0xFFFFFFFFul;
    long max_long = (long)(max_ulong >> 1);
    long min_long = -max_long - 1;

    unsigned long long max_ull = 0xFFFFFFFFFFFFFFFFull;
    long long max_ll = (long long)(max_ull >> 1);
    long long min_ll = -max_ll - 1;

    printf("=== Tipi interi ===\n");

    if ((char)-1 < 0) {
        signed char max_char = (signed char)(max_uchar >> 1);
        signed char min_char = (signed char)(-max_char - 1);
        printf("char:               sizeof = %zu, min = %d, max = %d\n", sizeof(char), min_char, max_char);
    } else {
        printf("char:               sizeof = %zu, min = %u, max = %u\n", sizeof(char), 0u, max_uchar);
    }

    printf("signed char:        sizeof = %zu, min = %d, max = %d\n", sizeof(signed char), min_schar, max_schar);
    printf("unsigned char:      sizeof = %zu, min = %u, max = %u\n", sizeof(unsigned char), 0u, max_uchar);

    printf("short:              sizeof = %zu, min = %d, max = %d\n", sizeof(short), min_short, max_short);
    printf("unsigned short:     sizeof = %zu, min = %u, max = %u\n", sizeof(unsigned short), 0u, max_ushort);

    printf("int:                sizeof = %zu, min = %d, max = %d\n", sizeof(int), min_int, max_int);
    printf("unsigned int:       sizeof = %zu, min = %u, max = %u\n", sizeof(unsigned int), 0u, max_uint);

    printf("long:               sizeof = %zu, min = %ld, max = %ld\n", sizeof(long), min_long, max_long);
    printf("unsigned long:      sizeof = %zu, min = %lu, max = %lu\n", sizeof(unsigned long), 0ul, max_ulong);

    printf("long long:          sizeof = %zu, min = %lld, max = %lld\n", sizeof(long long), min_ll, max_ll);
    printf("unsigned long long: sizeof = %zu, min = %llu, max = %llu\n", sizeof(unsigned long long), 0ull, max_ull);

    printf("\n=== Tipi in virgola mobile ===\n");
    printf("float:              sizeof = %zu, min = %e, max = %e\n", sizeof(float), -__FLT_MAX__, __FLT_MAX__);
    printf("double:             sizeof = %zu, min = %e, max = %e\n", sizeof(double), -__DBL_MAX__, __DBL_MAX__);
    printf("long double:        sizeof = %zu, min = %Le, max = %Le\n", sizeof(long double), -__LDBL_MAX__, __LDBL_MAX__);

    return 0;
}