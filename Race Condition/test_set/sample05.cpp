#include <pthread.h>
#include <cstdio>

void* behavior_one( void* arg )
{
    int* val = (int*)arg;
    for(int i=0; i<10; i++)
        (*val) += 3; 
    return NULL;
}

void* behavior_two( void* arg )
{
    int* val = (int*)arg;
    for(int i=0; i<10; i++)
        (*val) *= 2;
    return NULL; 
}

int main()
{
    int arg = 0;
    pthread_t one;
    pthread_t two;
    pthread_create( &one, NULL, behavior_one,  &arg );
    pthread_create( &two, NULL, behavior_two,  &arg );
    pthread_join( one, NULL );
    pthread_join( two, NULL );
    printf( "%d\n", arg );
    return 0;
}
