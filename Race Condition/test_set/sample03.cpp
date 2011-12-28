#include <pthread.h>
#include <cstdio>

void* behavior( void* arg )
{
    int* val = (int*)arg;
    if((*val) == 0)
        (*val) += 2;
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
