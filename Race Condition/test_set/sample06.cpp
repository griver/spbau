#include <pthread.h>
#include <cstdio>

void* behavior_one( void* arg )
{
    int* val = (int*)arg;

    if( (*val) % 2 )
        (*val) = 7;
    else 
        (*val) += 3;

    return NULL;
}

void* behavior_two( void* arg )
{
    int* val = (int*)arg;
    if( (*val) % 2 )
        (*val) = -7;
    else 
        (*val) -= 5;
    return NULL; 
}

int main()
{
    int arg = 2;
    pthread_t one;
    pthread_t two;
    pthread_create( &one, NULL, behavior_one,  &arg );
    pthread_create( &two, NULL, behavior_two,  &arg );
    pthread_join( one, NULL );
    pthread_join( two, NULL );
    printf( "%d\n", arg );
    return 0;
}
