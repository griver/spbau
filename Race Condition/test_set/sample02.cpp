#include <pthread.h>
#include <cstdio>

void* behavior( void* arg )
{
    int* val = (int*)arg;
    while( (*val) < 1000 )
        (*val)++;
    return NULL;
}

int main()
{
    int arg = 0;
    pthread_t one;
    pthread_t two;
    pthread_create( &one, NULL, behavior,  &arg );
    pthread_create( &two, NULL, behavior,  &arg );
    pthread_join( one, NULL );
    pthread_join( two, NULL );
    printf( "%d\n", arg );
    return 0;
}
